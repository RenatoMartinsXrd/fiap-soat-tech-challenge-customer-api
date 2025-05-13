package com.hexagonalarch.shared.config;

import com.hexagonalarch.adapters.controllers.CustomerController;
import com.hexagonalarch.core.ports.gateways.*;
import com.hexagonalarch.core.ports.usecases.Customer.CreateCustomerUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.GetAllCustomersUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.GetCustomerUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.IdentifyOrCreateCustomerUseCasePort;
import com.hexagonalarch.core.usecases.Customer.CreateCustomerUseCase;
import com.hexagonalarch.core.usecases.Customer.GetAllCustomersUseCase;
import com.hexagonalarch.core.usecases.Customer.GetCustomerUseCase;
import com.hexagonalarch.core.usecases.Customer.IdentifyOrCreateCustomerUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CustomerController customerController(
            CreateCustomerUseCasePort createCustomerUseCase,
            GetCustomerUseCasePort getCustomerUseCase,
            GetAllCustomersUseCasePort getAllCustomersUseCase,
            IdentifyOrCreateCustomerUseCasePort identifyOrCreateCustomerUseCase) {
        return new CustomerController(
                createCustomerUseCase,
                getCustomerUseCase,
                getAllCustomersUseCase,
                identifyOrCreateCustomerUseCase);
    }

    @Bean
    public CreateCustomerUseCasePort createCustomerUseCasePort(CustomerGatewayPort customerGatewayPort) {
        return new CreateCustomerUseCase(customerGatewayPort);
    }

    @Bean
    public GetCustomerUseCasePort getCustomerUseCasePort(CustomerGatewayPort customerGatewayPort) {
        return new GetCustomerUseCase(customerGatewayPort);
    }

    @Bean
    public GetAllCustomersUseCasePort getAllCustomersUseCasePort(CustomerGatewayPort customerGatewayPort) {
        return new GetAllCustomersUseCase(customerGatewayPort);
    }

    @Bean
    public IdentifyOrCreateCustomerUseCasePort identifyOrCreateCustomerUseCasePort(CustomerGatewayPort customerGatewayPort) {
        return new IdentifyOrCreateCustomerUseCase(customerGatewayPort);
    }
}
