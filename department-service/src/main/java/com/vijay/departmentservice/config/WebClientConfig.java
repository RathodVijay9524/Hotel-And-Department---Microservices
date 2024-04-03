package com.vijay.departmentservice.config;

import com.vijay.departmentservice.client.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;


    @Bean
    public WebClient empWebClient() {
        return WebClient.builder().baseUrl("http://EMPLOYEE-SERVICE")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public EmployeeClient inventoryClient() {
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(empWebClient())).build()
                .createClient(EmployeeClient.class);
    }
}
