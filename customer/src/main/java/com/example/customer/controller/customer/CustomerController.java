package com.example.customer.controller.customer;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    protected final CustomerService service;

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@CookieValue("CUSTOMER") String authenticatedUser, @RequestBody Customer customerToUpdate){
        this.service.updateCustomer(authenticatedUser,customerToUpdate);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@CookieValue("CUSTOMER") String authenticatedUser){
        this.service.deleteCustomer(authenticatedUser);
    }



    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(){}
}
