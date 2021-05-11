package menu.repository;

import exceptions.CuisinesNotFoundException;
import exceptions.MenuItemNotFoundException;
import menu.models.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Simple hardcoded repository for program test purposes.
 */
public class InMemoryMenuRepository implements MenuRepository {

    private final List<MainCourse> availableMainCourses = Arrays.asList(
            new MainCourse(1,"Spaghetti", 19.99F, "", CuisinesType.ITALIAN),
            new MainCourse(2,"Lasagne", 15.99F, "Available meat type: beef, pork.", CuisinesType.ITALIAN),
            new MainCourse(3,"Pasta with Pesto", 16.99F, "", CuisinesType.ITALIAN),
            new MainCourse(4,"Dumplings", 10.99F, "Available with: meat, goat cheese, cabbage and blueberries.", CuisinesType.POLISH),
            new MainCourse(5,"Pork Chop with Potatoes", 22.99F, "", CuisinesType.POLISH),
            new MainCourse(6,"Burrito", 8.99F, "Available spicy types: mild, medium, hot, extra hot", CuisinesType.MEXICAN),
            new MainCourse(7,"Spicy BBQ Beef", 29.99F, "", CuisinesType.MEXICAN)
    );

    private final List<Dessert> availableDesserts = Arrays.asList(
            new Dessert(1,"Ice Cream", 9.99F, "", false),
            new Dessert(2,"Cheese Cake", 10.99F, "", true),
            new Dessert(3,"Tiramisu", 11.99F, "", false),
            new Dessert(4,"Sugarfree Fresh Fruit Cocktail", 12.99F, "", true)
    );

    private final List<Drink> availableDrinks = Arrays.asList(
            new Drink(1,"Coca-Cola", 2.99F, "", false),
            new Drink(2,"Fanta", 2.99F, "", false),
            new Drink(3,"Mojito", 2.99F, "", true),
            new Drink(4,"Old Fashioned", 2.99F, "", true)
    );

    @Override
    public List<MainCourse> getAllMainCourse() {
        return availableMainCourses;
    }

    @Override
    public List<MainCourse> getMainCoursesByCuisinesTypeId(int cuisinesId) throws CuisinesNotFoundException {
        Optional<CuisinesType> typeOptional = Arrays.stream(CuisinesType.values())
                .filter(cuisinesType -> cuisinesType.index == cuisinesId)
                .findFirst();

        if (typeOptional.isEmpty()) {
            throw new CuisinesNotFoundException("Cuisines with id: " + cuisinesId + " not found.");
        }

        return this.availableMainCourses.stream()
                .filter(mainCourse -> mainCourse.getCuisinesType() == typeOptional.get())
                .collect(Collectors.toList());
    }

    @Override
    public List<Dessert> getAllDeserts() {
        return availableDesserts;
    }

    @Override
    public List<Dessert> getFitDesserts() {
        return this.availableDesserts.stream()
                .filter(Dessert::isFit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Drink> getAllDrinks() {
        return this.availableDrinks;
    }

    @Override
    public List<Drink> getDrinksWithAlcohol() {
        return this.availableDrinks.stream()
                .filter(Drink::isWithAlcohol)
                .collect(Collectors.toList());
    }

    @Override
    public MenuItem findMainCoursePriceById(int id) throws MenuItemNotFoundException {
        List<? extends MenuItem> availableMainCourses = this.availableMainCourses;

        return findItemOrThrowException((List<MenuItem>) availableMainCourses, id);
    }

    @Override
    public MenuItem findDessertPriceById(int id) throws MenuItemNotFoundException {
        List<? extends MenuItem> availableDesserts = this.availableDesserts;

        return findItemOrThrowException((List<MenuItem>) availableDesserts, id);
    }

    @Override
    public MenuItem findDrinkPriceById(int id) throws MenuItemNotFoundException {
        List<? extends MenuItem> availableDesserts = this.availableDesserts;

        return findItemOrThrowException((List<MenuItem>) availableDesserts, id);
    }

    private MenuItem findItemOrThrowException(List<MenuItem> collection, int searchedId) throws MenuItemNotFoundException {
        Optional<MenuItem> optionalMenuItem = collection.stream()
                .filter(menuItem -> menuItem.getId() == searchedId)
                .findFirst();

        if (optionalMenuItem.isEmpty()) {
            throw new MenuItemNotFoundException("Menu item with id: " + searchedId + " not found.");
        }

        return optionalMenuItem.get();
    }
}
