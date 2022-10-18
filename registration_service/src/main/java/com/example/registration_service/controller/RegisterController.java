package com.example.registration_service.controller;

import com.example.registration_service.model.Customer;
import com.example.registration_service.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController()
@RequiredArgsConstructor()
public class RegisterController {

    protected final RegistrationService registrationService;

    @PostMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public Customer register(@RequestBody Customer c) throws Exception {
        return registrationService.registerCustomer(c);
    }

    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public Customer login(@RequestBody Customer customerToLogin, @Autowired HttpServletResponse response) throws Exception {
        return registrationService.login(customerToLogin,response);
    }

    @DeleteMapping(path = "/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletResponse logoutRequestResponse) throws Exception {
        registrationService.logout(logoutRequestResponse);
    }
}
