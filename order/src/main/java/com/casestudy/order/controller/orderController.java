package com.casestudy.order.controller;

import com.casestudy.order.exceptionHandlers.APIRequestException;
import com.casestudy.order.models.OrderDetails;
import com.casestudy.order.repository.orderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class orderController {

    @Autowired
    private orderRepository or;
    //to get all orders
    @GetMapping("/findall")
    public List<OrderDetails> findallOrders(){
        return or.findAll();
    }
    //to view the details of order by admin
    @GetMapping("/findOne/{orderId}")
    public OrderDetails findById(@PathVariable String orderId){
        OrderDetails order=or.findById(orderId).orElseThrow(()-> new APIRequestException("order is not found"));
        return order;
    }
    //to delete order
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Map<String, Boolean>> deleteOrderById(@PathVariable String orderId){
        OrderDetails order= or.findById(orderId).orElseThrow(()-> new APIRequestException("order with id "+orderId+"not found,delete failed"));
        or.delete(order);
        Map<String, Boolean> response = new HashMap<>();
        response.put("order deleted" , Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    // to book order
    @PostMapping("/add")
    public OrderDetails addOrder(@RequestBody OrderDetails order){
        order.setStatus("NA");
        order.setWasherName("NA");
        return or.save(order);
    }
    //to get order that are not assigned by admin to washer
    @GetMapping("/findUnassignedOrders")
    public List<OrderDetails> getUnassignedOrders(){
        return or.findAll().stream().filter(x->x.getWasherName().contains("NA")).collect(Collectors.toList());
    }
    //to update the status of order
    @PutMapping("/updateStatus/{orderId}")
    public ResponseEntity<OrderDetails> updateStatus(@PathVariable String orderId){
        OrderDetails existingOrder=or.findById(orderId).orElseThrow(()->new APIRequestException("order with id" + orderId+" not found,fail"));
        existingOrder.setStatus("completed"/*status*/);
        OrderDetails order=or.save(existingOrder);
        return ResponseEntity.ok(order);
    }
    //update the details of order
    @PutMapping("/update/{orderId}")
    public ResponseEntity<OrderDetails> updateOrder(@PathVariable String orderId, @RequestBody OrderDetails orderDetails){
        OrderDetails existingOrder=or.findById(orderId).orElseThrow(()->new APIRequestException("order with id" + orderId+" not found,fail"));
        existingOrder.setWashpack(orderDetails.getWashpack());
        existingOrder.setCars(orderDetails.getCars());
        existingOrder.setUseremailid(orderDetails.getUseremailid());
        existingOrder.setAreapincode(orderDetails.getAreapincode());
        existingOrder.setPhoneNo(orderDetails.getPhoneNo());
        existingOrder.setCars(orderDetails.getCars());
        OrderDetails updateOrder=or.save(existingOrder);
        return ResponseEntity.ok(updateOrder);
    }
    //to cancel the order by customer
    @PutMapping("/cancelOrder/{orderId}")
    public String cancelOrder(@PathVariable String orderId){
        OrderDetails od=or.findById(orderId).orElseThrow(()->new APIRequestException("order with id"+orderId+"not found,fail"));
        od.setStatus("cancelled");
        or.save(od);
        return " the order is cancel successfully";
    }
    //to get orders self
    @GetMapping("/findMyOrders/{useremailid}")
    public List<OrderDetails> getMyOrders(@PathVariable String useremailid){
        return or.findAll().stream().filter(x ->x.getUseremailid().contains(useremailid)).collect(Collectors.toList());
    }
    //To find all the completed orders
    @GetMapping("/findCompleted")
    public List<OrderDetails> getCompletedOrders(){
        return or.findAll().stream().filter(x -> x.getStatus().contains("Completed")).collect(Collectors.toList());
    }
    //To find all the pending orders
    @GetMapping("/findPending")
    public List<OrderDetails> getPendingOrders(){
        return or.findAll().stream().filter(x -> x.getStatus().contains("Pending")).collect(Collectors.toList());
    }
    //To find all the cancelled orders
    @GetMapping("/findCancelled")
    public List<OrderDetails> getCancelledOrders(){
        return or.findAll().stream().filter(x -> x.getStatus().contains("Cancelled")).collect(Collectors.toList());
    }
    //to assign the washer
    @PutMapping("/assignwasher/{orderId}")
    public ResponseEntity<OrderDetails> assignWasher(@PathVariable String orderId,@RequestBody OrderDetails od){
        OrderDetails existingOrder=or.findById(od.getOrderId()).orElseThrow(null);
        if(existingOrder.getWasherName().contains("NA")){
            existingOrder.setWasherName(od.getWasherName());
            existingOrder.setStatus("pending");
            or.save(existingOrder);
            return ResponseEntity.ok(existingOrder);
        }
        else {
            throw new APIRequestException("washer to that order assigned already");
        }

    }
}
