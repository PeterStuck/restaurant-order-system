package order;

import menu.models.CuisinesType;
import menu.models.Dessert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.NoSuchObjectException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void whenCreateOrderListShouldBeEmpty() {
        assertThat(this.order.getOrder(), hasSize(0));
    }

    @Test
    void shouldAddItemToOrderWhenItemIsValid() throws NoSuchObjectException {
        var item = new OrderItem(new Dessert(1,"TEST", 99.0F, "TEST", true), 1);
        this.order.addItemToOrder(item);

        assertThat(this.order.getOrder(), hasSize(1));
        assertThat(this.order.getOrder().get(0), equalTo(item));
    }

    @Test
    void whenItemIsNullShouldThrowException() {
        assertThrows(NoSuchObjectException.class, () -> {
            this.order.addItemToOrder(null);
        });
    }

    @Test
    void shouldRemoveItemFromOrderListWhenItemIsValid() throws NoSuchObjectException {
        var item = new OrderItem(new Dessert(2,"TEST", 99.0F, "TEST", false), 1);
        this.order.getOrder().add(item);

        this.order.removeItemFromOrder(item);

        assertThat(this.order.getOrder(), hasSize(0));
    }

    @Test
    void whenAttemptToRemoveNullableItemFromOrderThrowException() {
        assertThrows(NoSuchObjectException.class, () -> {
            this.order.removeItemFromOrder(null);
        });
    }
}