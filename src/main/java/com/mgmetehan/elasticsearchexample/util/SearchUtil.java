package com.mgmetehan.elasticsearchexample.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.function.Supplier;

@UtilityClass
public class SearchUtil {

    public static Supplier<Query> createSupplierAutoSuggest(String partialProductName) {

        Supplier<Query> supplier = () -> Query.of(q -> q.match(createAutoSuggestMatchQuery(partialProductName)));
        return supplier;
    }

    public static MatchQuery createAutoSuggestMatchQuery(String partialProductName) {
        val autoSuggestQuery = new MatchQuery.Builder();
        return autoSuggestQuery.field("name").query(partialProductName).analyzer("standard").build();
    }
}
