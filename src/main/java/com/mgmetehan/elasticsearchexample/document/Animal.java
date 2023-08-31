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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(indexName = "animal_index")
@Setting(settingPath = "static/es-settings.json")
public class Animal {
    @Id
    private String id;
    @Field(name = "name", type = FieldType.Text)
    private String name;
    @Field(name = "lastname", type = FieldType.Text)
    private String lastname;
    @Field(name = "age", type = FieldType.Integer)
    private Integer age;
}
