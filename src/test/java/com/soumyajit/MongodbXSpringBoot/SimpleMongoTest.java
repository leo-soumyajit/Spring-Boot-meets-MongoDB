package com.soumyajit.MongodbXSpringBoot;

import com.soumyajit.MongodbXSpringBoot.Entity.Order;
import com.soumyajit.MongodbXSpringBoot.Repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;

import java.util.List;

@SpringBootTest
public class SimpleMongoTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testCreateOrder(){
        for (int i = 1 ; i<10; i++){
            Order order = Order.builder()
                    .status("READY")
                    .quantity(2*i)
                    .totalPrice((100.0*i)-1)
                    .build();
            order = orderRepository.insert(order);
        }
    }

    @Test
    public void testFindByStatus(){
        List<Order> orders = orderRepository.findByStatus("PENDING");
        orders.forEach(System.out::println);
    }

    @Test
    public void testFindByStatusOrderByCreatedDesc(){
        List<Order> orders = orderRepository.findByStatusOrderByCreatedAtDesc("PENDING");
        orders.forEach(System.out::println);
    }

    @Test
    public void testForUsingQueryAnnotation(){
        List<Order> orders = orderRepository.findPendingOrdersAbovePrice("PENDING",10);
        orders.forEach(System.out::println);
    }

    @Test
    public void testDeleteOrder(){
        orderRepository.deleteById("68cb11bde20fa2446baccf66");
    }

    @Test
    public void testPageAndSort(){
        Pageable pageReq = PageRequest.of(0,5, Sort.by(Sort.Direction.DESC,"totalPrice"));
        List<Order> orders = orderRepository.findAll(pageReq).toList();
        orders.forEach(System.out::println);
    }

}
