package com.example.customer_gateway.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Customer {
    @Id
    protected String username;
    protected String aboutMe;
    protected String location;
}
