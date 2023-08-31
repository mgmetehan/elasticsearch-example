package com.mgmetehan.elasticsearchexample.repository;

import com.mgmetehan.elasticsearchexample.document.Animal;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AnimalRepository extends ElasticsearchRepository<Animal, String> {
    @Query("{\"bool\": {\"should\": [{\"match\": {\"name\": \"?0\"}}, {\"match\": {\"lastname\": \"?0\"}}, {\"match\": {\"age\": \"?0\"}}]}}")
    List<Animal> searchWithFields(String searchTerm);

    @Query("{\"bool\": {\"should\": [{\"match\": {\"name\": \"?0\"}}, {\"match\": {\"lastname\": \"?0\"}}]}}")
    List<Animal> searchNameAndLastName(String searchTerm);
}
