package menu.models;

public class MainCourse extends MenuItem {

    private final CuisinesType cuisinesType;

    public MainCourse(int id, String name, Float price, String shortDescription, CuisinesType cuisinesType) {
        super(id, name, price, shortDescription);
        this.cuisinesType = cuisinesType;
    }

    public CuisinesType getCuisinesType() {
        return cuisinesType;
    }

}
