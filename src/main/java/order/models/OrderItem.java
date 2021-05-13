package order.models;

import menu.models.Orderable;

public class OrderItem {

    private Orderable orderableItem;
    private int quantity;

    public OrderItem(Orderable orderableItem, int quantity) {
        this.orderableItem = orderableItem;
        this.quantity = quantity;
    }

    public Orderable getItem() {
        return orderableItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
