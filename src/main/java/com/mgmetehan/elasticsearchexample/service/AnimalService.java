package com.mgmetehan.elasticsearchexample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgmetehan.elasticsearchexample.document.Animal;
import com.mgmetehan.elasticsearchexample.dto.SearchRequestDto;
import com.mgmetehan.elasticsearchexample.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final ObjectMapper mapper;

    public Animal indexAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal findAnimalById(Long id) {
        return animalRepository.findById(String.valueOf(id)).orElseThrow(() -> new RuntimeException("Animal not found"));
    }


    public List<Animal> searchAnimals(SearchRequestDto searchRequest) {
        try {
            if (searchRequest.getFields() != null && !searchRequest.getFields().isEmpty()) {
                if (searchRequest.getFields().contains("name") || searchRequest.getFields().contains("lastname")) {
                    return animalRepository.searchNameAndLastName(searchRequest.getSearchValue());
                } else {
                    return animalRepository.searchWithFields(searchRequest.getSearchValue());
                }
            } else {
                return animalRepository.searchWithFields(searchRequest.getSearchValue());
            }
        } catch (Exception e) {
            // Hata yönetimi burada yapılabilir
            return Collections.emptyList();
        }
    }
}
