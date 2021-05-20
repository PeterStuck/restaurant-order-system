package order.console;

import java.util.Scanner;

/**
 * Allows to get from user certain options about order.
 */
public class ConsoleUserInteractor {

    private Scanner scanner;

    ConsoleUserInteractor() {
        this.scanner = new Scanner(System.in);
        this.printUILogo();
    }

    private void printUILogo() {
        System.out.println(" ________\n" +
                "   -------+  \\\n" +
                "   \\\\\\\\\\\\\\\\\\  \\\n" +
                "   //_//__\\\\\\  |\n" +
                "   /\\0' `0~ |\\ /\n" +
                "   (|^<, ^  .)|\n" +
                "    ( ._.  ||/\n" +
                "     \\ .  / |\n" +
                "      +--/  | ");
    }

    String askForLunch() {
        System.out.println("Hello, would you like to order lunch? [Y]es/[N]o");
        return scanner.nextLine().toLowerCase();
    }

    int askForCuisinesType() {
        System.out.println("What type of cuisines you like? (0- SHOW ALL, 1- POLISH, 2- MEXICAN, 3- ITALIAN)");
        return Integer.parseInt(scanner.nextLine());
    }

    int askForMainCourseIndex() {
        System.out.println("Please select main course you want and type it's number: ");
        return Integer.parseInt(scanner.nextLine());
    }

    String askForDessertType() {
        System.out.println("What type of desserts you like? ([S]HOW ALL, [F]IT)");
        return scanner.nextLine().toLowerCase();
    }

    int askForDessertIndex() {
        System.out.println("Please select dessert you want and type it's number: ");
        return Integer.parseInt(scanner.nextLine());
    }

    String askForDrink() {
        System.out.println("Would you like to order a drink? [Y]es/[N]o");
        return scanner.nextLine().toLowerCase();
    }

    int askForDrinkIndex() {
        System.out.println("Please select drink you want and type it's number: ");
        return Integer.parseInt(scanner.nextLine());
    }

    String askForAdditionalIceCubesForDrink() {
        System.out.println("Add ice cubes for drink? [Y]es/[N]o");
        return scanner.nextLine();
    }


    public String askForAdditionalLemonForDrink() {
        System.out.println("Add lemon to drink? [Y]es/[N]o");
        return scanner.nextLine();
    }
}
