package menu.printer;

import exceptions.CuisinesNotFoundException;
import menu.models.MenuItem;
import menu.repository.menu.InMemoryMenuRepository;
import menu.repository.menu.MenuRepository;

import java.util.List;


/**
 * Print format: [ID] |\t[NAME] |\t[SHORT_DESCRIPTION] |\t[PRICE]
 * Short description only shows up if menu item has it.
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
        if (cuisinesId == 0) {
            this.printAllMainCourses();
        } else {
            printItemsInMenu(this.repository.getMainCoursesByCuisinesTypeId(cuisinesId));
        }
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

    private void printItemsInMenu(List<?> collection) {
        List<? extends MenuItem> converted = (List<MenuItem>) collection;
        for (MenuItem menuItem : converted) {
            System.out.println(
                    menuItem.getId() + " |\t" +
                    menuItem.getName() + " |\t" +
                    (!menuItem.getShortDescription().equals("") ? menuItem.getShortDescription() + " |\t" : "") +
                    menuItem.getPrice());
        }
    }

}
