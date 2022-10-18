package com.example.payment.controller;

import com.example.payment.exceptions.InsufficientFundsException;
import com.example.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    protected final PaymentService paymentService;

    @PostMapping("/{username}/{amount}")
    @ResponseStatus(HttpStatus.OK)
    public void payForGoods(@PathVariable()  String username, @PathVariable Double amount) throws Exception{
        this.paymentService.makePayment(username,amount);
    }

    @PutMapping("/{username}/{amount}")
    @ResponseStatus(HttpStatus.OK)
    public void refund(@PathVariable()  String username, @PathVariable Double amount) throws Exception{
        this.paymentService.refund(username,amount);
    }


    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  String handler(Exception e){
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  void handler(){ }

}
