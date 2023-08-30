package com.mgmetehan.elasticsearchexample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgmetehan.elasticsearchexample.document.Animal;
import com.mgmetehan.elasticsearchexample.dto.SearchRequestDto;
import com.mgmetehan.elasticsearchexample.repository.AnimalRepository;
import com.mgmetehan.elasticsearchexample.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnimalService {

    private final RestHighLevelClient client;
    private final AnimalRepository animalRepository;
    private final ObjectMapper MAPPER = new ObjectMapper();

    public Animal indexAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal findAnimalById(Long id) {
        return animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal not found"));
    }


    public List<Animal> searchAnimals(SearchRequestDto dto) {
        // Arama istegi olusturuluyor
        SearchRequest searchRequest = buildAnimalSearchRequest(dto);

        // Arama istegi basariyla olusturulmussa devam edilir, aksi takdirde bos liste doner
        if (searchRequest == null) {
            log.error("Arama istegi olusturulamadi");
            return Collections.emptyList();
        }

        try {
            // Arama istegi gerceklestiriliyor
            SearchResponse searchResponse = executeSearchRequest(searchRequest);

            // Arama yanitindan hayvanlari cikartma islemi
            return extractAnimalsFromResponse(searchResponse);
        } catch (Exception e) {
            // Arama sirasinda hata olusursa hata loglanir ve bos liste doner
            log.error("Arama sirasinda hata olustu: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    // Arama istegi olusturma adimi
    private SearchRequest buildAnimalSearchRequest(SearchRequestDto dto) {
        return SearchUtil.buildSearchRequestForIndex("animal_index", dto);
    }

    // Arama istegini gerceklestirme adimi
    private SearchResponse executeSearchRequest(SearchRequest searchRequest) {
        try {
            return client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("Arama istegi sirasinda hata olustu: " + e.getMessage(), e);
            return null;
        }
    }

    // Arama yanitindan hayvanlari cikartma adimi
    private List<Animal> extractAnimalsFromResponse(SearchResponse searchResponse) {
        try {
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            List<Animal> animals = new ArrayList<>(searchHits.length);

            // Her arama vurusundan bir hayvan cikartiliyor ve listeye ekleniyor
            for (SearchHit hit : searchHits) {
                Animal animal = MAPPER.readValue(hit.getSourceAsString(), Animal.class);
                animals.add(animal);
            }
            return animals;
        } catch (Exception e) {
            log.error("Arama yaniti sirasinda hata olustu: " + e.getMessage(), e);
            return null;
        }
    }

}
