package com.mgmetehan.elasticsearchexample.repository;

import com.mgmetehan.elasticsearchexample.document.Order;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderRepository extends ElasticsearchRepository<Order, String> {
    //category
    @Query("{\"match\":{\"category\":\"?0\"}}")
    Iterable<Order> findByCategory(String category);

}
