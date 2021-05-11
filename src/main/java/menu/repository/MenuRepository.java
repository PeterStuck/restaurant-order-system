package menu.repository;

import exceptions.MenuItemNotFoundException;
import menu.models.*;

import java.util.List;

public interface MenuRepository {

    List<MainCourse> getAllMainCourse();

    List<MainCourse> getMainCoursesByCuisinesType(CuisinesType type);

    MenuItem findMainCoursePriceById(int id) throws MenuItemNotFoundException;

    List<Dessert> getAllDeserts();

    List<Dessert> getFitDesserts();

    MenuItem findDessertPriceById(int id) throws MenuItemNotFoundException;

    List<Drink> getAllDrinks();

    List<Drink> getDrinksWithAlcohol();

    MenuItem findDrinkPriceById(int id) throws MenuItemNotFoundException;

}
