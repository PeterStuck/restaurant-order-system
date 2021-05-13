package menu.models;

public class Drink extends MenuItem implements Orderable {

    private boolean withLemon;
    private boolean withIceCubes;

    public Drink(int id, String name, Float price, String shortDescription, boolean withLemon, boolean withIceCubes) {
        super(id, name, price, shortDescription);
        this.withLemon = withLemon;
        this.withIceCubes = withIceCubes;
    }

    public boolean isWithLemon() {
        return withLemon;
    }

    public void setWithLemon(boolean withLemon) {
        this.withLemon = withLemon;
    }

    public boolean isWithIceCubes() {
        return withIceCubes;
    }

    public void setWithIceCubes(boolean withIceCubes) {
        this.withIceCubes = withIceCubes;
    }

    @Override
    public Orderable getItem() {
        return this;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "withLemon=" + withLemon +
                ", withIceCubes=" + withIceCubes +
                '}';
    }
}
