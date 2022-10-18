package com.example.registration_service.service;

import com.example.registration_service.model.Customer;
import com.example.registration_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    protected final CustomerRepository repository;
    protected final BCryptPasswordEncoder encoder;



    public Customer registerCustomer(Customer returnCustomer) throws Exception
    {
        if(!this.repository.existsByUsername(returnCustomer.getUsername()))
        {
            String encodedPassword = encoder.encode(returnCustomer.getPassword());
            Customer cToSave = new Customer(returnCustomer.getUsername(),encodedPassword,returnCustomer.getAboutMe());
            returnCustomer = repository.save(cToSave);
            repository.flush();
        }
        else
            throw new Exception("Username is taken.");

        return returnCustomer;
    }

    public Customer login(Customer customerToLogin, HttpServletResponse response) throws Exception {
        Optional<Customer> customerOptional = this.repository.findById(customerToLogin.getUsername());
        Customer discoveredCustomer = null;
        if(customerOptional.isPresent())
        {
           if( encoder.matches(customerToLogin.getPassword(),customerOptional.get().getPassword())) {
               Cookie cookie = new Cookie("CUSTOMER",customerToLogin.getUsername());
               cookie.setPath("/");
               response.addCookie(cookie);
               discoveredCustomer = customerOptional.get();
           }
           else
               throw new Exception("Username and password does not match.");
        }
        else
            throw new Exception("User does not exist.");

        return discoveredCustomer;
    }

    public void logout(HttpServletResponse logoutRequestResponse)
    {
        logoutRequestResponse.addCookie(new Cookie("CUSTOMER",""));
    }

}
