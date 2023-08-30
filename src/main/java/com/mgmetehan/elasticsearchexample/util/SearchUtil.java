package com.mgmetehan.elasticsearchexample.util;

import com.mgmetehan.elasticsearchexample.dto.SearchRequestDto;
import lombok.experimental.UtilityClass;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.util.CollectionUtils;

import java.util.List;
@UtilityClass
public class SearchUtil {

    /**
     * Bu metod, belirli bir Elasticsearch endeksi için bir arama isteği oluşturur.
     *
     * @param indexName          Elasticsearch endeks adı
     * @param searchRequestDTO   Arama isteği veri transfer nesnesi
     * @return                   Oluşturulan arama isteği (SearchRequest)
     */
    public static SearchRequest buildSearchRequestForIndex(String indexName, SearchRequestDto searchRequestDTO) {
        try {
            // Arama istegi olustur
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                    .postFilter(buildQuery(searchRequestDTO));

            // SearchRequest nesnesini olustur ve SearchSourceBuilder ile doldur
            return new SearchRequest(indexName).source(sourceBuilder);
        } catch (Exception e) {
            e.printStackTrace();
             return null;
        }
    }

    /**
     * Bu metod, belirli bir arama istegi veri transfer nesnesine gore bir sorgu olusturur.
     *
     * @param searchRequestDTO   Arama istegi veri transfer nesnesi
     * @return                   Olusturulan sorgu (QueryBuilder)
     */
    private static QueryBuilder buildQuery(SearchRequestDto searchRequestDTO) {
        if (searchRequestDTO == null) {
            return null;
        }

        List<String> fields = searchRequestDTO.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return null;
        }

        if (fields.size() > 1) {
            // Birden fazla alanda coklu alan aramasi olustur
            MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(searchRequestDTO.getSearchValue())
                    //"John" adinda bir kisiyi ariyorsunuz ve bu isim hem "ad" hem de "soyad" alanlarinda bulunabilir.
                    .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                    .operator(Operator.AND);

            // Her bir alana alan adini ekle
            fields.forEach(queryBuilder::field);

            return queryBuilder;
        } else {
            // Tek bir alanda alan aramasi olustur
            return fields.stream()
                    .findFirst() // Ilk alana odaklaniyoruz.
                    .map(field -> QueryBuilders.matchQuery(field, searchRequestDTO.getSearchValue())
                            .operator(Operator.AND)) // Secilen alanda terim eslesmesi saglayan bir matchQuery olusturuyoruz.
                    .orElse(null); // Eger herhangi bir alan secilmemisse veya sorgu olusturulamamissa null donduruyoruz.
        }
    }
}
