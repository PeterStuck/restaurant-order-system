package menu.repository.menu;

import exceptions.CuisinesNotFoundException;
import exceptions.MenuItemNotFoundException;
import menu.models.MenuItem;
import menu.repository.menu_items.DessertRepository;
import menu.repository.menu_items.DrinkRepository;
import menu.repository.menu_items.MainCourseRepository;
import menu.repository.menu_items.MenuItemRepository;

import java.util.List;

/**
 * Simple hardcoded repository for program test purposes.
 */
public class InMemoryMenuRepository implements MenuRepository {


    private MenuItemRepository mainCourseRepo;
    private MenuItemRepository dessertRepo;
    private MenuItemRepository drinkRepo;

    public InMemoryMenuRepository() {
        mainCourseRepo = new MainCourseRepository();
        drinkRepo = new DrinkRepository();
        dessertRepo = new DessertRepository();
    }

    @Override
    public List<MenuItem> getAllMainCourse() {
        return this.mainCourseRepo.getAll();
    }

    @Override
    public List<MenuItem> getMainCoursesByCuisinesTypeId(int cuisinesId) throws CuisinesNotFoundException {
        return ((MainCourseRepository) this.mainCourseRepo).getMainCoursesByCuisinesTypeId(cuisinesId);
    }

    @Override
    public List<MenuItem> getAllDeserts() {
        return this.dessertRepo.getAll();
    }

    @Override
    public List<MenuItem> getFitDesserts() {
        return ((DessertRepository) this.dessertRepo).getFitDesserts();
    }

    @Override
    public List<MenuItem> getAllDrinks() {
        return this.drinkRepo.getAll();
    }

    @Override
    public MenuItem findMainCourseById(int id) throws MenuItemNotFoundException {
        return this.mainCourseRepo.findItemById(id);
    }

    @Override
    public MenuItem findDessertById(int id) throws MenuItemNotFoundException {
        return this.dessertRepo.findItemById(id);
    }

    @Override
    public MenuItem findDrinkById(int id) throws MenuItemNotFoundException {
        return this.drinkRepo.findItemById(id);
    }

}
