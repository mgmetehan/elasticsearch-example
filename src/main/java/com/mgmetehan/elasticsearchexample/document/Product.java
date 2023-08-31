package com.mgmetehan.elasticsearchexample.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    private String id;
    private String category;
    private Float basePrice;
    private Float baseUnitPrice;
    private Date createdOn;
    private Float discountAmount;
    private Float discountPercentage;
    private String manufacturer;
    private Float minPrice;
    private Float price;
    private Long productId;
    private String productName;
    private Integer quantity;
    private String sku;
    private Float taxAmount;
    private Float taxfulPrice;
    private Float taxlessPrice;
    private Float unitDiscountAmount;
}