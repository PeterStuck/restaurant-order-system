package order.printer;

import order.models.Order;

import java.rmi.NoSuchObjectException;

/**
 * Interface to show console message of order summary.
 */
public interface OrderSummaryPrinter {

    void printOrderSummary(Order order) throws NoSuchObjectException;

}
