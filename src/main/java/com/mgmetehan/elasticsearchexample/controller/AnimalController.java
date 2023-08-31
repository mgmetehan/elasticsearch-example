package com.mgmetehan.elasticsearchexample.controller;

import com.mgmetehan.elasticsearchexample.document.Animal;
import com.mgmetehan.elasticsearchexample.document.Order;
import com.mgmetehan.elasticsearchexample.dto.SearchRequestDto;
import com.mgmetehan.elasticsearchexample.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;


    @GetMapping("/search")
    public List<Animal> searchAnimals(@RequestBody SearchRequestDto dto) {
        return animalService.searchAnimals(dto);
    }

    @GetMapping()
    public Iterable<Order> find() {
        return animalService.findAll();
    }

}
