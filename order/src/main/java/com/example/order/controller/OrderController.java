package com.example.order.controller;

import com.example.order.model.Order;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
public class OrderController {

    protected final OrderService service;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders(@CookieValue("CUSTOMER") String authenticatedUser)
    {
        return this.service.getAllOrders(authenticatedUser);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public Order postOrder(@CookieValue("CUSTOMER") String authenticatedUser,@RequestBody Order orderToSubmit){

        return this.service.postOrder(authenticatedUser, orderToSubmit);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Order putOrder(@CookieValue("CUSTOMER") String authenticatedUser, @RequestBody Order orderToSubmit) throws Exception {
        return this.service.putOrder(authenticatedUser,orderToSubmit);
    }

    @ExceptionHandler(Exception.class)
    public void  handleException(){}
}

