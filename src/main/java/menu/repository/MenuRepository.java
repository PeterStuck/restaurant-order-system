package menu.repository;

import exceptions.CuisinesNotFoundException;
import exceptions.MenuItemNotFoundException;
import menu.models.*;

import java.util.List;

/**
 * Fake JPA repository template
 */
public interface MenuRepository {

    List<MainCourse> getAllMainCourse();

    List<MainCourse> getMainCoursesByCuisinesTypeId(int cuisinesId) throws CuisinesNotFoundException;

    MenuItem findMainCourseById(int id) throws MenuItemNotFoundException;

    List<Dessert> getAllDeserts();

    List<Dessert> getFitDesserts();

    MenuItem findDessertById(int id) throws MenuItemNotFoundException;

    List<Drink> getAllDrinks();

    MenuItem findDrinkById(int id) throws MenuItemNotFoundException;

}
