package order.models;

import menu.models.*;
import order.models.Order;
import order.models.OrderItem;
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
        assertThat(this.order.getOrderList(), hasSize(0));
    }

    @Test
    void shouldAddItemToOrderWhenItemIsValid() throws NoSuchObjectException {
        var item = new Drink(1,"TEST", 99.0F, "TEST", false, false);
        this.order.addItemToOrder(item);

        assertThat(this.order.getOrderList(), hasSize(1));
    }

    @Test
    void shouldIncreaseQuantityOfOrderItemIfItemAlreadyExistsInList() throws NoSuchObjectException {
        order.addItemToOrder(new Drink(1, "TEST", 0F, "", false, false));
        order.addItemToOrder(new Drink(1, "TEST", 0F, "", false, false));

        assertThat(order.getOrderList(), hasSize(1));
        assertThat(order.getOrderList().get(0).getQuantity(), equalTo(2));
    }

    @Test
    void shouldIncreaseQuantityIfOrderedLunchAlreadyExists() throws NoSuchObjectException {
        order.addItemToOrder(new Lunch(
                        new MainCourse(1, "TEST", 0F, "", CuisinesType.POLISH),
                        new Dessert(1, "TEST", 0F, "", false)
                ));
        order.addItemToOrder(new Lunch(
                new MainCourse(1, "TEST", 0F, "", CuisinesType.POLISH),
                new Dessert(1, "TEST", 0F, "", false)
        ));

        assertThat(order.getOrderList(), hasSize(1));
        assertThat(order.getOrderList().get(0).getQuantity(), equalTo(2));
    }

    @Test
    void shouldAllowToHaveDifferentOrderableClassesInOrderList() throws NoSuchObjectException {
        order.addItemToOrder(new Lunch(
                new MainCourse(1, "TEST", 0F, "", CuisinesType.POLISH),
                new Dessert(1, "TEST", 0F, "", false)
        ));
        order.addItemToOrder(new Drink(1, "TEST", 0F, "", false, false));

        assertThat(order.getOrderList(), hasSize(2));
    }

    @Test
    void shouldAllowToIncreaseQuantityOfDifferentOrderableClassesInOrderList() throws NoSuchObjectException {
        order.addItemToOrder(new Lunch(
                new MainCourse(1, "TEST", 0F, "", CuisinesType.POLISH),
                new Dessert(1, "TEST", 0F, "", false)
        ));
        order.addItemToOrder(new Lunch(
                new MainCourse(1, "TEST", 0F, "", CuisinesType.POLISH),
                new Dessert(1, "TEST", 0F, "", false)
        ));
        order.addItemToOrder(new Drink(1, "TEST", 0F, "", false, false));
        order.addItemToOrder(new Drink(1, "TEST", 0F, "", false, false));

        assertThat(order.getOrderList(), hasSize(2));
        assertThat(order.getOrderList().get(0).getQuantity(), equalTo(2));
        assertThat(order.getOrderList().get(1).getQuantity(), equalTo(2));
    }

    @Test
    void whenItemIsNullShouldThrowException() {
        assertThrows(NoSuchObjectException.class, () -> {
            this.order.addItemToOrder(null);
        });
    }

    @Test
    void shouldRemoveItemFromOrderListWhenItemIsValid() throws NoSuchObjectException {
        var item = new OrderItem(new Drink(1,"TEST", 99.0F, "TEST", false, false), 1);
        this.order.getOrderList().add(item);

        this.order.removeItemFromOrder(item);

        assertThat(this.order.getOrderList(), hasSize(0));
    }

    @Test
    void whenAttemptToRemoveNullableItemFromOrderThrowException() {
        assertThrows(NoSuchObjectException.class, () -> {
            this.order.removeItemFromOrder(null);
        });
    }
}