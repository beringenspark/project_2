package com.example.customer.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import models.AbstractCustomer;

import javax.persistence.Entity;

@Entity
@Data @NoArgsConstructor
public class Customer extends AbstractCustomer {
}
