package com.hexagonalarch.core.usecases.Customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetAllCustomersUseCaseTest {

    private CustomerGatewayPort customerGatewayPort;
    private GetAllCustomersUseCase getAllCustomersUseCase;

    @BeforeEach
    void setUp() {
        customerGatewayPort = mock(CustomerGatewayPort.class);
        getAllCustomersUseCase = new GetAllCustomersUseCase(customerGatewayPort);
    }

    @Test
    void shouldReturnListOfCustomers() {
        Customer customer1 = new Customer();
        customer1.setName("Daniel");

        Customer customer2 = new Customer();
        customer2.setName("Ana");

        List<Customer> expectedList = List.of(customer1, customer2);

        when(customerGatewayPort.findAll()).thenReturn(expectedList);

        List<Customer> result = getAllCustomersUseCase.getAllCustomers();

        verify(customerGatewayPort).findAll();
        assertThat(result).hasSize(2).containsExactly(customer1, customer2);
    }
}
