package menu.repository.menu_items;

import menu.models.Dessert;
import menu.models.MenuItem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DessertRepository extends MenuItemRepository {

    private final List<MenuItem> availableDesserts = Arrays.asList(
            new Dessert(1,"Ice Cream", 9.99F, "", false),
            new Dessert(2,"Cheese Cake", 10.99F, "", true),
            new Dessert(3,"Tiramisu", 11.99F, "", false),
            new Dessert(4,"Sugarfree Fresh Fruit Cocktail", 12.99F, "", true)
    );

    public DessertRepository() {
        this.menuItems = availableDesserts;
    }

    public List<MenuItem> getFitDesserts() {
        return this.availableDesserts.stream()
                .filter(dessert -> ((Dessert) dessert).isFit())
                .collect(Collectors.toList());
    }
}
