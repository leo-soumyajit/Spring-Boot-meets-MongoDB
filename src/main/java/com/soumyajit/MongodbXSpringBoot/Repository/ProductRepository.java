package com.soumyajit.MongodbXSpringBoot.Repository;

import com.soumyajit.MongodbXSpringBoot.Entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
