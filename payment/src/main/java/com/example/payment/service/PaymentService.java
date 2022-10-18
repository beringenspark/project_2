package com.example.payment.service;

import com.example.payment.model.Customer;
import com.example.payment.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    protected final CustomerRepository repository;

    public void makePayment(String customerName, Double amount) throws Exception {
        Customer c = this.repository.findCustomerByUsername(customerName);
        if( c == null || c.getCredits() < amount)
            throw new Exception("Insufficient funds in account");
        else
            c.setCredits(c.getCredits() - amount);

        repository.save(c);
    }

    public void refund(String customer, Double amount) throws Exception
    {
        Customer c = this.repository.findCustomerByUsername(customer);
        if( c == null)
            throw new Exception("Requested Customer not discovered when posting refund to account.");
        else
            c.setCredits(c.getCredits() + amount);

        repository.save(c);
    }
}
