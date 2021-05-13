package order.printer;

import menu.models.Lunch;
import menu.models.MenuItem;
import order.models.Order;
import order.models.OrderItem;
import order.utils.OrderTotalCalculator;

import java.rmi.NoSuchObjectException;

/**
 * Simple class to print order summary.
 * Print item format: [NAME] |\t[PRICE] |\t[QUANTITY]
 */
public class SimpleOrderSummaryPrinter implements OrderSummaryPrinter {

    @Override
    public void printOrderSummary(Order order) throws NoSuchObjectException {
        System.out.println("\n\nORDER SUMMARY");
        System.out.println("_______________________");

        for (OrderItem orderItem : order.getOrderList()) {
            printOrderItemDetails(orderItem);
        }

        System.out.println("_______________________");
        System.out.println("TOTAL \t\t" + OrderTotalCalculator.computeOrderTotalPrice(order));
    }

    private void printOrderItemDetails(OrderItem orderItem) {
        if (orderItem.getItem() instanceof Lunch) {
            Lunch item = (Lunch) orderItem.getItem();
            System.out.println(item.getMainCourse().getName() + " |\t" + item.getMainCourse().getPrice() + " |\t" + orderItem.getQuantity());
            System.out.println(item.getDessert().getName() + " |\t" + item.getDessert().getPrice() + " |\t" + orderItem.getQuantity());
        } else {
            MenuItem item = (MenuItem) orderItem.getItem();
            System.out.println(item.getName() + " |\t" + item.getPrice() + " |\t" + orderItem.getQuantity());
        }
    }

}
