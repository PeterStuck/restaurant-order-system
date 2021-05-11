package menu.models;

public enum CuisinesType {

    POLISH(1),
    MEXICAN(2),
    ITALIAN(3);

    public final int index;

    private CuisinesType(int index) {
        this.index = index;
    }


}
