package com.mgmetehan.elasticsearchexample.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GeoIP {
    private String cityname;
    private String continentname;
    private String countryisocode;
    private GeoPoint location;
    private String regionname;

}
