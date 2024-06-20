package com.training.region_api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "cities")
@Accessors(chain = true)
@NoArgsConstructor
@Data
public class City {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Indexed
    private String name;

    private String province;

}
