package com.mgmetehan.elasticsearchexample.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.mgmetehan.elasticsearchexample.document.Animal;
import com.mgmetehan.elasticsearchexample.dto.SearchRequestDto;
import com.mgmetehan.elasticsearchexample.service.AnimalService;
import com.mgmetehan.elasticsearchexample.service.ESService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;
    private final ESService esService;

    @PostMapping
    public Animal indexAnimal(@RequestBody Animal animal) {
        return animalService.indexAnimal(animal);
    }

    @GetMapping("/{id}")
    public Animal findAnimalById(@PathVariable Long id) {
        return animalService.findAnimalById(id);
    }

    @GetMapping("/search")
    public List<Animal> searchAnimals(@RequestBody SearchRequestDto searchRequest) {
        try {
            return animalService.searchAnimals(searchRequest);
        } catch (Exception e) {
            // Hata yönetimi burada yapılabilir
            return Collections.emptyList();
        }
    }

    @GetMapping("/suggestions/{input}")
    public List<String> getAutocompleteSuggestions(@PathVariable String input) {
        return animalService.getAutocompleteSuggestions(input);
    }

    @GetMapping("/autoSuggest/{animalName}")
    List<String> autoSuggestAnimalSearch(@PathVariable String animalName) {
        try {
            SearchResponse<Animal> searchResponse = esService.autoSuggestProduct(animalName);
            List<Hit<Animal>> hitList = searchResponse.hits().hits();
            List<Animal> animalList = new ArrayList<>();
            for (Hit<Animal> hit : hitList) {
                animalList.add(hit.source());
            }
            List<String> listOfProductNames = new ArrayList<>();
            for (Animal animal : animalList) {
                listOfProductNames.add(animal.getName());
            }
            return listOfProductNames;
        } catch (Exception e) {
            // Hata yönetimi burada yapılabilir
            return Collections.emptyList();
        }
    }
}
