package com.example.customer_gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory{

    @Override
    public GatewayFilter apply(Object config) {

        return (exchange, chain) -> {

            ServerHttpRequest req = exchange.getRequest();
            HttpCookie authToken = req.getCookies().getFirst("CUSTOMER");
            Mono<Void> retVal = null;

            System.out.println("Filter executing.");
            if(authToken != null)
            {
                retVal = chain.filter(exchange);
            }
            else
            {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                retVal = exchange.getResponse().setComplete();
            }

            return retVal;
        };
    }

}
