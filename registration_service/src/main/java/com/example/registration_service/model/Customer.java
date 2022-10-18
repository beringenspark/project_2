package com.example.registration_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.AbstractCustomer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.List;

@Entity()
@AllArgsConstructor
public class Customer extends AbstractCustomer {

    public Customer(String username, String encodedPassword, String aboutMe) {
        this.username = username;
        this.password = encodedPassword;
        this.aboutMe = aboutMe;
        this.credits = 1000.0;
    }
}
