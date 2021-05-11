package order;

import menu.models.Drink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.NoSuchObjectException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTotalCalculatorTest {

    private Order order;

    @BeforeEach
    void setUp() {
        this.order = new Order();
    }

    @Test
    void shouldReturnValidOrderTotalValue() throws NoSuchObjectException {
        order.addItemToOrder(new OrderItem(new Drink(1,"TEST", 1.0F, "TEST", true), 1));
        order.addItemToOrder(new OrderItem(new Drink(2,"TEST2", 2.0F, "TEST", false), 1));
        order.addItemToOrder(new OrderItem(new Drink(3,"TEST3", 3.0F, "TEST", true), 2));

        assertThat(OrderTotalCalculator.computeOrderTotalPrice(order), equalTo(9.0F));
    }

    @Test
    void shouldReturnZeroWhenOrderListIsEmpty() throws NoSuchObjectException {
        assertThat(OrderTotalCalculator.computeOrderTotalPrice(order), equalTo(0F));
    }

    @Test
    void shouldThrowExceptionWhenPassedOrderIsOfTypeNull() {
        assertThrows(NoSuchObjectException.class, () -> {
            OrderTotalCalculator.computeOrderTotalPrice(null);
        });
    }

}