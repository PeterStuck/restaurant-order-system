import exceptions.CuisinesNotFoundException;
import exceptions.MenuItemNotFoundException;
import order.OrderReciever;
import order.printer.OrderSummaryPrinter;
import order.printer.SimpleOrderSummaryPrinter;

import java.rmi.NoSuchObjectException;

public class RestaurantOrderSystemApplication {

    private OrderReciever orderReciever;
    private OrderSummaryPrinter orderSummaryPrinter;

    public RestaurantOrderSystemApplication() {
        this.orderReciever = new OrderReciever();
        this.orderSummaryPrinter = new SimpleOrderSummaryPrinter();
    }

    public void makeOrder() throws MenuItemNotFoundException, NoSuchObjectException, CuisinesNotFoundException {
        this.orderReciever.pickUpOrder();
        this.orderSummaryPrinter.printOrderSummary(this.orderReciever.getOrder());
    }

    public static void main(String[] args) throws MenuItemNotFoundException, NoSuchObjectException, CuisinesNotFoundException {
        RestaurantOrderSystemApplication orderSystem = new RestaurantOrderSystemApplication();
        orderSystem.makeOrder();
    }

}
