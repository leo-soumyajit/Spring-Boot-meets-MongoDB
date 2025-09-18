package com.soumyajit.MongodbXSpringBoot;

import com.soumyajit.MongodbXSpringBoot.Entity.Address;
import com.soumyajit.MongodbXSpringBoot.Entity.Order;
import com.soumyajit.MongodbXSpringBoot.Entity.Product;
import com.soumyajit.MongodbXSpringBoot.Repository.OrderRepository;
import com.soumyajit.MongodbXSpringBoot.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RelationshipMongoTests {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testOrderCreation(){

        Product laptop = Product.builder()
                .name("Gaming Laptop")
                .category("Electronics")
                .price(1299.99)
                .build();
        laptop = productRepository.save(laptop);

        Product mouse = Product.builder()
                .name("Wireless Mouse")
                .category("Accessories")
                .price(49.99)
                .build();
        mouse = productRepository.save(mouse);



        Order order = Order.builder()
                .status("READY")
                .quantity(20)
                .totalPrice((700.0)-1)
                .products(List.of(laptop,mouse))
                .address(Address.builder()
                        .city("Mumbai")
                        .line1("XYZ Lane")
                        .state("Maharashtra")
                        .zipCode("891201")
                        .country("India")
                        .build())
                .build();
        order = orderRepository.insert(order);

        System.out.println(order);
    }

    @Test
    public void testFindBy(){
//        List<Order> orderList = orderRepository.findByAddressCity("Mumbai");
        List<Order> orderList = orderRepository.findByCity("Mumbai");
        orderList.forEach(System.out::println);
    }
}


