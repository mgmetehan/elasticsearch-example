package com.mgmetehan.elasticsearchexample.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(indexName = "kibana_sample_data_ecommerce")
@Setting(settingPath = "static/es-settings.json")
public class Order {
    @Id
    private String id;
    @Field(type = FieldType.Auto, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String category;
    private String currency;
    private Date customerBirthDate;
    private String customerFirstName;
    private String customerFullName;
    private String customerGender;
    private String customerId;
    private String customerLastName;
    private String customerPhone;
    private String dayOfWeek;
    private Integer dayOfWeekI;
    private String email;
    private GeoIP geoip;
    private String manufacturer;
    private Date orderDate;
    private String orderId;
    private List<Product> products;
    private String sku;
    private Float taxfulTotalPrice;
    private Float taxlessTotalPrice;
    private Integer totalQuantity;
    private Integer totalUniqueProducts;
    private String type;
    private String user;
}