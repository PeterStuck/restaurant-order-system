package menu.models;

public class Drink extends MenuItem {

    private boolean withAlcohol;

    public Drink(int id, String name, Float price, String shortDescription, boolean withAlcohol) {
        super(id, name, price, shortDescription);
        this.withAlcohol = withAlcohol;
    }

    public boolean isWithAlcohol() {
        return withAlcohol;
    }

    public void setWithAlcohol(boolean withAlcohol) {
        this.withAlcohol = withAlcohol;
    }
}
