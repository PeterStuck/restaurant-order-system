package order.models;

import menu.models.*;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class to hold ordered items and handle quantity change.
 */
public class Order {

    private List<OrderItem> order;

    public Order() {
        this.order = new ArrayList<>();
    }

    public void addItemToOrder(Orderable item) throws NoSuchObjectException {
        if (item == null) {
            throw new NoSuchObjectException("OrderItem cannot be nullable!");
        }

        if (item.getItem() instanceof Lunch) {
            this.addLunchToOrderListOrIncreaseQuantity((Lunch) item);
        } else {
            this.addItemToOrderListOrIncreaseQuantity(item);
        }
    }

    public void removeItemFromOrder(OrderItem item) throws NoSuchObjectException {
        if (item == null) {
            throw new NoSuchObjectException("OrderItem cannot be nullable!");
        }

        this.order.remove(item);
    }

    /**
     * Special method for Lunch class. In result of it's structure ( build by two other objects ) cannot be parsed into MenuItem class.
     * @param orderedLunch
     * @see Lunch
     */
    private void addLunchToOrderListOrIncreaseQuantity(Lunch orderedLunch) {
        Optional<OrderItem> optionalOrderItem = this.order.stream()
                .filter(orderItem -> orderItem.getItem() instanceof Lunch)
                .filter(orderItem -> {
                    MainCourse mainCourse = ((Lunch) orderItem.getItem()).getMainCourse();
                    return mainCourse.getId() == (orderedLunch.getMainCourse().getId());
                })
                .filter(orderItem -> {
                    Dessert dessert = ((Lunch) orderItem.getItem()).getDessert();
                    return dessert.getId() == (orderedLunch.getDessert().getId());
                }).findFirst();

        this.increaseQuantityOrAddNewItemToOrder(optionalOrderItem, orderedLunch);
    }

    private void addItemToOrderListOrIncreaseQuantity(Orderable orderedItem) {
        Optional<OrderItem> optionalOrderItem = this.order.stream()
                .filter(orderItem -> !(orderItem.getItem() instanceof Lunch))
                .filter(orderItem -> {
                    MenuItem item = (MenuItem) orderItem.getItem();
                    return item.getId() == (((MenuItem) orderedItem.getItem()).getId());
                }).findFirst();

        this.increaseQuantityOrAddNewItemToOrder(optionalOrderItem, orderedItem);
    }

    /**
     * When orderItem was found in order then increase it's quantity, otherwise add new item to order.
     * @param orderItem
     * @param newOrderItem
     */
    private void increaseQuantityOrAddNewItemToOrder(Optional<OrderItem> orderItem, Orderable newOrderItem) {
        if (orderItem.isPresent()) {
            this.increaseQuantityOfOrderItem(orderItem.get());
        } else {
            this.order.add(new OrderItem(newOrderItem, 1));
        }
    }

    private void increaseQuantityOfOrderItem(OrderItem orderItem) {
        int indexElementToReplace = this.order.indexOf(orderItem);
        orderItem.setQuantity(orderItem.getQuantity() + 1);
        this.order.set(indexElementToReplace, orderItem);
    }

    public List<OrderItem> getOrderList() {
        return this.order;
    }

}
