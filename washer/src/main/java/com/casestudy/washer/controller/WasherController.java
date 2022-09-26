package com.casestudy.washer.controller;

import com.casestudy.order.controller.orderController;
import com.casestudy.washer.model.OrderDetails;
import com.casestudy.washer.model.WashPacks;
import com.casestudy.washer.service.WasherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/washers")
public class WasherController {

    Logger logger = LoggerFactory.getLogger(WasherController.class);


    @Autowired
    WasherService wr;

    //To see the Unassigned orders
    @GetMapping("/findUnassigned")
    public List<OrderDetails> getUnassignedOrders(){
        logger.info("controller accessed");
        return wr.getUnassignedOrders();
    }
    //The status of the order can be either pending or completed
    @PutMapping("/updateStatus")
    public OrderDetails updateStatusoftheOrder(@RequestBody OrderDetails orderDetails){
        return wr.updateStatus(orderDetails);
    }
    //To assign a washer to the order by washer
    @PutMapping("/assign")
    public OrderDetails assignSelf(@RequestBody OrderDetails orderDetails){
        return wr.assignSelf(orderDetails);
    }
    //To see all the wash packs
    @GetMapping("/seeWP")
    public List<WashPacks> getAllWP(){
        return wr.getAllWP();
    }

}
