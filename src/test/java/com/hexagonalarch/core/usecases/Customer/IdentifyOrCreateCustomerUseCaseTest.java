package com.hexagonalarch.core.usecases.Customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.usecases.strategy.NavigationResult;
import com.hexagonalarch.core.usecases.strategy.customer.CustomerStrategy;
import com.hexagonalarch.core.usecases.strategy.customer.factories.IdentifyOrCreateCustomerFactory;
import com.hexagonalarch.shared.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class IdentifyOrCreateCustomerUseCaseTest {

    private CustomerGatewayPort customerGateway;
    private IdentifyOrCreateCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        customerGateway = mock(CustomerGatewayPort.class);
        useCase = new IdentifyOrCreateCustomerUseCase(customerGateway);
    }

    @Test
    void shouldReturnCustomerWhenStrategyIsSuccessful() {
        Customer input = new Customer();
        CustomerStrategy strategy = mock(CustomerStrategy.class);
        Customer expected = new Customer();
        expected.setName("Daniel");

        NavigationResult<Customer> result = NavigationResult.success(expected, null);

        try (MockedConstruction<IdentifyOrCreateCustomerFactory> mockedFactory =
                     mockConstruction(IdentifyOrCreateCustomerFactory.class,
                             (factoryMock, context) -> when(factoryMock.getStrategy(input)).thenReturn(strategy))) {

            when(strategy.execute(input)).thenReturn(result);

            Customer returned = useCase.identifyOrCreateCustomer(input);

            assertThat(returned).isEqualTo(expected);
        }
    }

    @Test
    void shouldThrowBusinessExceptionWhenStrategyFails() {
        Customer input = new Customer();
        CustomerStrategy strategy = mock(CustomerStrategy.class);

        NavigationResult<Customer> result = NavigationResult.failure("Erro de validação");

        try (MockedConstruction<IdentifyOrCreateCustomerFactory> mockedFactory =
                     mockConstruction(IdentifyOrCreateCustomerFactory.class,
                             (factoryMock, context) -> when(factoryMock.getStrategy(input)).thenReturn(strategy))) {

            when(strategy.execute(input)).thenReturn(result);

            BusinessException exception = assertThrows(BusinessException.class, () -> {
                useCase.identifyOrCreateCustomer(input);
            });

            assertThat(exception.getMessage()).isEqualTo("Erro de validação");
        }
    }
}
