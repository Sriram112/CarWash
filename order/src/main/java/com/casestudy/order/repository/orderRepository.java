package com.casestudy.order.repository;

import com.casestudy.order.models.OrderDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface orderRepository extends MongoRepository<OrderDetails,String> {

}
