package menu.repository.menu_items;

import exceptions.MenuItemNotFoundException;
import menu.models.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MenuItemRepository {

    protected List<MenuItem> menuItems;

    public MenuItemRepository() {
        menuItems = new ArrayList<>();
    }

    public List<MenuItem> getAll() {
        return this.menuItems;
    }

    public MenuItem findItemById(int id) throws MenuItemNotFoundException {
        return findItemOrThrowException(this.menuItems, id);
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
