package com.soumyajit.MongodbXSpringBoot.Repository;

import com.soumyajit.MongodbXSpringBoot.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findByStatus(String status);
    List<Order> findByStatusOrderByCreatedAtDesc(String status);

    @Query("{ 'status': ?0, 'totalPrice': { $gte: ?1 } }")
    List<Order> findPendingOrdersAbovePrice(String status, double minPrice);

    List<Order> findByAddressCity(String city);

    @Query(value = "{'address.city': ?0}", fields = "{ '_id': 1, 'quantity': 1 }")
    List<Order> findByCity(String city);

}
