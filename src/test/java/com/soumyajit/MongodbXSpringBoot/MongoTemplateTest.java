package com.soumyajit.MongodbXSpringBoot;

import com.soumyajit.MongodbXSpringBoot.Entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MongoTemplateTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void mongoTemplateTest(){
        List<Order> orderList = mongoTemplate.findAll(Order.class);
        orderList.forEach(System.out::println);
    }

    @Test
    public void mongoTemplateTestUsingCriteria1(){
        Query query = new Query(
                Criteria.where("status").in("PENDING")
                        .and("totalPrice").lt(699)
        );
        List<Order> orderList = mongoTemplate.find(query,Order.class);
        orderList.forEach(System.out::println);
    }

    @Test
    public void mongoTemplateTestUsingCriteria2(){
        Query query = new Query(
               new Criteria().orOperator(
                       Criteria.where("totalPrice").lt(600),
                       Criteria.where("status").is("PENDING")
               )
        );
        query.fields().include("status","id","totalPrice");
        List<Order> orderList = mongoTemplate.find(query,Order.class);
        orderList.forEach(System.out::println);
    }

    @Test
    public void bulkUpdate(){
        Query query = new Query(
                Criteria.where("status").is("READY")
        );
        Update update = new Update().set("status","SHIPPED")
                .set("updatedAt",new Date());
        mongoTemplate.updateMulti(query,update, Order.class);
    }
}
