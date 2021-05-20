package order;

import exceptions.CuisinesNotFoundException;
import exceptions.MenuItemNotFoundException;
import menu.models.Dessert;
import menu.models.Drink;
import menu.models.Lunch;
import menu.models.MainCourse;
import menu.repository.menu.InMemoryMenuRepository;
import menu.repository.menu.MenuRepository;
import order.console.ConsoleMessageManager;
import order.models.Order;

import java.rmi.NoSuchObjectException;

/**
 * Manages operations between inputs and repository, and hold Order object.
 */
public class OrderReciever {

    private MenuRepository menuRepository;
    private ConsoleMessageManager messageManager;
    private Order order;

    public OrderReciever() {
        this.menuRepository = new InMemoryMenuRepository();
        this.messageManager = new ConsoleMessageManager();
        this.order = new Order();
    }

    public void pickUpOrder() throws CuisinesNotFoundException, MenuItemNotFoundException, NoSuchObjectException {
        this.takeLunchOrder();
        this.takeDrinkOrder();
    }

    private void takeLunchOrder() throws NoSuchObjectException, CuisinesNotFoundException, MenuItemNotFoundException {
        if (this.messageManager.isLunchSelected()) {
            int mainCourseId = this.messageManager.askForMainCourseAndGetSelectedId();
            int dessertId = this.messageManager.askForDessertAndGetSelectedId();

            MainCourse orderedMainCourse = (MainCourse) this.menuRepository.findMainCourseById(mainCourseId);
            Dessert orderedDessert = (Dessert) this.menuRepository.findDessertById(dessertId);
            Lunch orderedLunch = new Lunch(orderedMainCourse, orderedDessert);

            this.order.addItemToOrder(orderedLunch);
        }
    }

    private void takeDrinkOrder() throws MenuItemNotFoundException, NoSuchObjectException {
        if (this.messageManager.isDrinkSelected()) {
            int drinkId = this.messageManager.askForDrinkAndGetSelectedId();

            Drink drink = (Drink) this.menuRepository.findDrinkById(drinkId);
            drink.setWithLemon(this.messageManager.isLemonRequestedForDrink());
            drink.setWithIceCubes(this.messageManager.isIceCubesRequestedForDrink());

            this.order.addItemToOrder(drink);
        }
    }

    public MenuRepository getMenuRepository() {
        return menuRepository;
    }

    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public ConsoleMessageManager getMessageManager() {
        return messageManager;
    }

    public void setMessageManager(ConsoleMessageManager messageManager) {
        this.messageManager = messageManager;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
