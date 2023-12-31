package com.mgmetehan.elasticsearchexample.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.mgmetehan.elasticsearchexample.document.Animal;
import com.mgmetehan.elasticsearchexample.util.SearchUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Supplier;


@Service
@RequiredArgsConstructor
@Slf4j
public class ESService {
    private final ElasticsearchClient elasticsearchClient;

    public SearchResponse<Animal> autoSuggestProduct(String animalName) throws IOException {

        Supplier<Query> supplier = SearchUtil.createSupplierAutoSuggest(animalName);
        SearchResponse<Animal> searchResponse = elasticsearchClient
                .search(s -> s.index("animal_index").query(supplier.get()), Animal.class);
        log.info(" elasticsearch auto suggestion query" + supplier.get().toString());
        return searchResponse;
    }
}
