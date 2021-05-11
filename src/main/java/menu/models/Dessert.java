package menu.models;

public class Dessert extends MenuItem {

    private boolean isFit;

    public Dessert(int id, String name, Float price, String shortDescription, boolean isFit) {
        super(id, name, price, shortDescription);
        this.isFit = isFit;
    }

    public boolean isFit() {
        return isFit;
    }

}
