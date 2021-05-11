package order;

import menu.models.MenuItem;

public class OrderItem {

    private MenuItem menuItem;
    private int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public Float getItemPrice() {
        return menuItem.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }
}
