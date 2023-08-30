package com.mgmetehan.elasticsearchexample.repository;

import com.mgmetehan.elasticsearchexample.document.Animal;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AnimalRepository extends ElasticsearchRepository<Animal, Long> {
}
