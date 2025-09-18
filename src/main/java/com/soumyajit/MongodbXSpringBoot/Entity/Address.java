package com.soumyajit.MongodbXSpringBoot.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
public class Address {
    private String line1;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
