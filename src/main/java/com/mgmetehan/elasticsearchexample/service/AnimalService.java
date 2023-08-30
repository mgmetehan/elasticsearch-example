package com.mgmetehan.elasticsearchexample.service;

import com.mgmetehan.elasticsearchexample.document.Animal;
import com.mgmetehan.elasticsearchexample.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService {

        private final AnimalRepository animalRepository;

        public Animal indexAnimal(Animal animal) {
            return animalRepository.save(animal);
        }

    public Animal findAnimalById(Long id) {
        return animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal not found"));
    }
}
