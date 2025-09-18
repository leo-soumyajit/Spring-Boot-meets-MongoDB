package com.soumyajit.MongodbXSpringBoot.Entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    private String name;

    @Indexed
    private String category;

    @Indexed
    private double price;


}
