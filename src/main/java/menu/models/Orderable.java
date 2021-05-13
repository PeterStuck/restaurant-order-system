package menu.models;

/**
 * Interface for menu item that allows to add to Order list
 */
public interface Orderable {

    Float getPrice();

    Orderable getItem();

}
