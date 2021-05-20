package menu.repository.menu_items;

import menu.models.Drink;
import menu.models.MenuItem;

import java.util.Arrays;
import java.util.List;

public class DrinkRepository extends MenuItemRepository {

    private final List<MenuItem> availableDrinks = Arrays.asList(
            new Drink(1,"Coca-Cola", 2.99F, "", false, false),
            new Drink(2,"Fanta", 2.99F, "", false, false),
            new Drink(3,"Orange Juice", 3.99F, "", false, false),
            new Drink(4,"Diet Coke", 1.99F, "", false, false),
            new Drink(5,"Water", 0.99F, "", false, false)
    );

    public DrinkRepository() {
        this.menuItems = availableDrinks;
    }



}
