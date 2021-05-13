package menu.printer;

import exceptions.CuisinesNotFoundException;

/**
 * Interface provided to show text representation of menu item objects in console
 */
public interface MenuPrinter {

    void printAllMainCourses();

    void printMainCoursesWithCuisinesId(int cuisinesId) throws CuisinesNotFoundException;

    void printAllDeserts();

    void printOnlyFitDesserts();

    void printAllDrinks();

}
