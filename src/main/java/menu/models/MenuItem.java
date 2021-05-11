package menu.models;

/**
 * Base class for all items in menu.
 */
public abstract class MenuItem {

    private int id;
    private String name;
    private Float price;
    private String shortDescription;

    protected MenuItem(int id, String name, Float price, String shortDescription) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shortDescription = shortDescription;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
