package order.console;

import exceptions.CuisinesNotFoundException;
import menu.printer.MenuPrinter;
import menu.printer.SimpleMenuPrinter;

/**
 * Class to merge ConsoleUserInteractor and MenuPrinter functionality ( Write and Print ).
 * @see ConsoleUserInteractor
 * @see MenuPrinter
 */
public class ConsoleMessageManager {

    private ConsoleUserInteractor userInteractor;
    private MenuPrinter menuPrinter;

    public ConsoleMessageManager() {
        this.userInteractor = new ConsoleUserInteractor();
        this.menuPrinter = new SimpleMenuPrinter();
    }

    public boolean isLunchSelected() {
        String lunchSelected = this.userInteractor.askForLunch();

        return optionSelected(lunchSelected);
    }

    public int askForMainCourseAndGetSelectedId() {
        try {
            int cuisinesId = this.userInteractor.askForCuisinesType();
            this.menuPrinter.printMainCoursesWithCuisinesId(cuisinesId);
            return this.userInteractor.askForMainCourseIndex();
        } catch (CuisinesNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Only numbers are accepted here.");
        }
        return this.askForMainCourseAndGetSelectedId();
    }

    public int askForDessertAndGetSelectedId() {
        try {
            String dessertType = this.userInteractor.askForDessertType();
            if (dessertType.equals("s") || dessertType.equals("show all")) {
                this.menuPrinter.printAllDeserts();
            } else if (dessertType.equals("f") || dessertType.equals("fit")) {
                this.menuPrinter.printOnlyFitDesserts();
            } else {
                throw new IllegalArgumentException("Can't find dessert type: " + dessertType);
            }
            return this.userInteractor.askForDessertIndex();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return this.askForDessertAndGetSelectedId();
        }
    }

    public boolean isDrinkSelected() {
        String drinkSelected = this.userInteractor.askForDrink();

        return optionSelected(drinkSelected);
    }

    public int askForDrinkAndGetSelectedId() {
        this.menuPrinter.printAllDrinks();

        return this.userInteractor.askForDrinkIndex();
    }

    public boolean isLemonRequestedForDrink() {
        String lemonSelected = this.userInteractor.askForAdditionalLemonForDrink();
        return optionSelected(lemonSelected);
    }

    public boolean isIceCubesRequestedForDrink() {
        String iceCubesSelected = this.userInteractor.askForAdditionalIceCubesForDrink();
        return optionSelected(iceCubesSelected);
    }

    private boolean optionSelected(String option) {
        return (option.equals("y") || option.equals("yes"));
    }

    public ConsoleUserInteractor getUserInteractor() {
        return userInteractor;
    }

    public void setUserInteractor(ConsoleUserInteractor userInteractor) {
        this.userInteractor = userInteractor;
    }

    public MenuPrinter getMenuPrinter() {
        return menuPrinter;
    }

    public void setMenuPrinter(MenuPrinter menuPrinter) {
        this.menuPrinter = menuPrinter;
    }

}
