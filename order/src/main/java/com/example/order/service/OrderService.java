package com.example.order.service;

import com.example.order.client.OrderClient;
import com.example.order.model.Order;
import com.example.order.model.Product;
import com.example.order.repository.OrderRepository;
import com.example.order.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    protected final OrderRepository repository;
    protected final OrderClient client;
    protected final ProductRepository productRepository;

    public List<Order> getAllOrders(String authenticatedUser)
    {
        return repository.findAllByOrderUsername(authenticatedUser);
    }

    public Order postOrder(String authenticatedUser, Order orderToPost)
    {
        Order orderToCreate = new Order();
        Product productToOrder = productRepository.findProductById(orderToPost.getProductId());
        orderToCreate.setOrderUsername(authenticatedUser);
        orderToCreate.setQuantity(orderToPost.getQuantity());
        orderToCreate.setProductId(productToOrder.getId());
        ResponseEntity<String> entity = client.attemptPayment(authenticatedUser,orderToCreate.getQuantity() * productToOrder.getCost());

        if(entity.getStatusCode() != HttpStatus.OK)
        {
            System.out.println("Order not succesful!");
        }
        return repository.save(orderToCreate);
    }
    public Order putOrder(String authenticatedUser, Order orderToUpdate) throws Exception {
        Order discoveredOrder = this.repository.findByOrderUsernameAndId(authenticatedUser,orderToUpdate.getId());

        if(discoveredOrder != null)
        {
            discoveredOrder.updateSelf(orderToUpdate);
            discoveredOrder =repository.save(discoveredOrder);
        }
        else
            throw new Exception(String.format("Order with id %d and username %s not discovered.",orderToUpdate.getId(),authenticatedUser));

        return discoveredOrder;
    }
    public void deleteOrder(String authenticatedUser,Long orderToDeleteId) throws Exception {
        Integer deleteCount = this.repository.deleteByOrderUsernameAndId(authenticatedUser,orderToDeleteId);

        if(deleteCount != 1)
            throw new Exception(String.format("Order with username %s and id %d was not discovered.",authenticatedUser,orderToDeleteId));
    }
}
