package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class CustomerService {

    protected final CustomerRepository repository;

    public void updateCustomer(String authenticatedUser, Customer customerToUpdate){
        Customer discoveredCustomer = this.repository.findCustomerByUsername(authenticatedUser);
        discoveredCustomer.setAboutMe(customerToUpdate.getAboutMe());
        this.repository.save(discoveredCustomer);
    }

    public void deleteCustomer(String authenticatedUser) {
        this.repository.deleteById(authenticatedUser);
    }
}
