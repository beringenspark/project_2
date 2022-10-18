package com.example.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment")
public interface OrderClient {

    @PostMapping( value = "/{username}/{amount}", consumes = "application/json")
    ResponseEntity<String> attemptPayment(@PathVariable("username") String username, @PathVariable Double amount);
}
