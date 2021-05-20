package menu.repository.menu;

import exceptions.CuisinesNotFoundException;
import exceptions.MenuItemNotFoundException;
import menu.models.*;

import java.util.List;

/**
 * Fake JPA repository template
 */
public interface MenuRepository {

    List<MenuItem> getAllMainCourse();

    List<MenuItem> getMainCoursesByCuisinesTypeId(int cuisinesId) throws CuisinesNotFoundException;

    MenuItem findMainCourseById(int id) throws MenuItemNotFoundException;

    List<MenuItem> getAllDeserts();

    List<MenuItem> getFitDesserts();

    MenuItem findDessertById(int id) throws MenuItemNotFoundException;

    List<MenuItem> getAllDrinks();

    MenuItem findDrinkById(int id) throws MenuItemNotFoundException;

}
