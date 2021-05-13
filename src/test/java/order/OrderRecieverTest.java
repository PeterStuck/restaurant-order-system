package order;

import exceptions.CuisinesNotFoundException;
import exceptions.MenuItemNotFoundException;
import order.console.ConsoleMessageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.NoSuchObjectException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderRecieverTest {

    private OrderReciever orderReciever;
    private ConsoleMessageManager messageManager;

    @BeforeEach
    void setUp() {
        orderReciever = new OrderReciever();
        messageManager = mock(ConsoleMessageManager.class);

        orderReciever.setMessageManager(messageManager);
    }

    @Test
    void shouldThrowExceptionWhenEnteredMainCourseIdNotExistsInMenu() throws CuisinesNotFoundException {
        when(messageManager.isLunchSelected()).thenReturn(true);
        when(messageManager.askForMainCourseAndGetSelectedId()).thenReturn(999);
        when(messageManager.askForDessertAndGetSelectedId()).thenReturn(1);

        assertThrows(MenuItemNotFoundException.class, () -> {
           orderReciever.pickUpOrder();
        });
    }

    @Test
    void shouldThrowExceptionWhenEnteredDessertIdNotExistsInMenu() throws CuisinesNotFoundException {
        when(messageManager.isLunchSelected()).thenReturn(true);
        when(messageManager.askForMainCourseAndGetSelectedId()).thenReturn(1);
        when(messageManager.askForDessertAndGetSelectedId()).thenReturn(999);

        assertThrows(MenuItemNotFoundException.class, () -> {
            orderReciever.pickUpOrder();
        });
    }

    @Test
    void shouldAddLunchToOrderWhenUserProvidesValidData() throws CuisinesNotFoundException, NoSuchObjectException, MenuItemNotFoundException {
        when(messageManager.isLunchSelected()).thenReturn(true);
        when(messageManager.askForMainCourseAndGetSelectedId()).thenReturn(1);
        when(messageManager.askForDessertAndGetSelectedId()).thenReturn(1);
        when(messageManager.isDrinkSelected()).thenReturn(false);

        orderReciever.pickUpOrder();

        assertThat(orderReciever.getOrder().getOrderList(), hasSize(1));
    }

    @Test
    void shouldThrowExceptionWhenEnteredDrinkIdNotExistsInMenu() {
        when(messageManager.isLunchSelected()).thenReturn(false);
        when(messageManager.isDrinkSelected()).thenReturn(true);
        when(messageManager.askForDrinkAndGetSelectedId()).thenReturn(999);

        assertThrows(MenuItemNotFoundException.class, () -> {
            orderReciever.pickUpOrder();
        });
    }

    @Test
    void shouldAddDrinkToOrderWhenUserProvidesValidData() throws CuisinesNotFoundException, NoSuchObjectException, MenuItemNotFoundException {
        when(messageManager.isLunchSelected()).thenReturn(false);
        when(messageManager.isDrinkSelected()).thenReturn(true);
        when(messageManager.askForDrinkAndGetSelectedId()).thenReturn(1);

        orderReciever.pickUpOrder();

        assertThat(orderReciever.getOrder().getOrderList(), hasSize(1));
    }



}