package order.utils;

import order.models.Order;
import order.models.OrderItem;

import java.rmi.NoSuchObjectException;
import java.util.List;

public class OrderTotalCalculator {

    public static Float computeOrderTotalPrice(Order order) throws NoSuchObjectException {
        if (order == null) {
            throw new NoSuchObjectException("Order cannot be nullable!");
        }

        List<OrderItem> orderItems = order.getOrderList();
        float orderTotal = 0.0F;

        for (OrderItem item : orderItems) {
            orderTotal += item.getItem().getPrice() * item.getQuantity();
        }

        return orderTotal;
    }

}
