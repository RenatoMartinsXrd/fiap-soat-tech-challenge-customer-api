package com.hexagonalarch.core.usecases.Customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class GetCustomerUseCaseTest {

    private CustomerGatewayPort customerGatewayPort;
    private GetCustomerUseCase getCustomerUseCase;

    @BeforeEach
    void setUp() {
        customerGatewayPort = mock(CustomerGatewayPort.class);
        getCustomerUseCase = new GetCustomerUseCase(customerGatewayPort);
    }

    @Test
    void shouldReturnCustomerWhenExists() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Daniel");

        when(customerGatewayPort.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = getCustomerUseCase.getCustomerById(1L);

        verify(customerGatewayPort).findById(1L);
        assertThat(result).isEqualTo(customer);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        when(customerGatewayPort.findById(999L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            getCustomerUseCase.getCustomerById(999L);
        });

        verify(customerGatewayPort).findById(999L);
        assertThat(exception.getMessage()).isEqualTo("Customer not found");
    }
}
