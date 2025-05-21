package com.hexagonalarch.core.usecases.Customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CreateCustomerUseCaseTest {

    private CustomerGatewayPort customerGateway;
    private CreateCustomerUseCase createCustomerUseCase;

    @BeforeEach
    void setUp() {
        customerGateway = mock(CustomerGatewayPort.class);
        createCustomerUseCase = new CreateCustomerUseCase(customerGateway);
    }

    @Test
    void shouldCallSaveAndReturnCreatedCustomer() {
        Customer input = new Customer();
        input.setName("Daniel");

        when(customerGateway.save(input)).thenReturn(input);

        Customer result = createCustomerUseCase.createCustomer(input);

        verify(customerGateway).save(input);
        assertThat(result).isEqualTo(input);
    }
}
