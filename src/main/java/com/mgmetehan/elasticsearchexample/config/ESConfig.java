package com.mgmetehan.elasticsearchexample.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.mgmetehan.elasticsearchexample.repository")
@ComponentScan(basePackages = {"com.mgmetehan.elasticsearchexample"})
public class ESConfig {

    @Value("${elasticsearch.url}")
    private String elasticsearchUrl;



}
