package menu.models;

public class Lunch {

    private MainCourse mainCourse;
    private Dessert dessert;

    public Lunch(MainCourse mainCourse, Dessert dessert) {
        this.mainCourse = mainCourse;
        this.dessert = dessert;
    }

    public void setMainCourse(MainCourse mainCourse) {
        this.mainCourse = mainCourse;
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }

    public MainCourse getMainCourse() {
        return mainCourse;
    }

    public Dessert getDessert() {
        return dessert;
    }
}
