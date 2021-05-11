package order;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderItem> order;

    public Order() {
        this.order = new ArrayList<>();
    }

    public void addItemToOrder(OrderItem item) throws NoSuchObjectException {
        if (item == null) {
            throw new NoSuchObjectException("OrderItem cannot be nullable!");
        }

        this.order.add(item);
    }

    public void removeItemFromOrder(OrderItem item) throws NoSuchObjectException {
        if (item == null) {
            throw new NoSuchObjectException("OrderItem cannot be nullable!");
        }

        this.order.remove(item);
    }

    public List<OrderItem> getOrder() {
        return this.order;
    }

}
