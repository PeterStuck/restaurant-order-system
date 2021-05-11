package menu.printer;

import exceptions.CuisinesNotFoundException;
import menu.models.Dessert;
import menu.models.MainCourse;
import menu.models.MenuItem;
import menu.repository.InMemoryMenuRepository;
import menu.repository.MenuRepository;

import java.util.List;


/**
 * Print format: [ID] |\t[NAME] |\t[PRICE]
 */
public class SimpleMenuPrinter implements MenuPrinter {

    private final MenuRepository repository;

    public SimpleMenuPrinter() {
        this.repository = new InMemoryMenuRepository();
    }

    public void printAllMainCourses() {
        printItemsInMenu(this.repository.getAllMainCourse());
    }

    public void printMainCoursesWithCuisinesId(int cuisinesId) throws CuisinesNotFoundException {
        printItemsInMenu(this.repository.getMainCoursesByCuisinesTypeId(cuisinesId));
    }

    public void printAllDeserts() {
        printItemsInMenu(this.repository.getAllDeserts());
    }

    public void printOnlyFitDesserts() {
        printItemsInMenu(this.repository.getFitDesserts());
    }

    public void printAllDrinks() {
        printItemsInMenu(this.repository.getAllDrinks());
    }

    public void printOnlyDrinksWithAlcohol() {
        printItemsInMenu(this.repository.getDrinksWithAlcohol());
    }

    private void printItemsInMenu(List<?> collection) {
        List<? extends MenuItem> converted = (List<MenuItem>) collection;
        for (MenuItem mainCourse : converted) {
            System.out.println(mainCourse.getId() + " |\t" + mainCourse.getName() + " |\t" + mainCourse.getPrice());
        }
    }

}
