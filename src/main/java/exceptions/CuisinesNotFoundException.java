package exceptions;

public class CuisinesNotFoundException extends Exception {

    public CuisinesNotFoundException() {
    }

    public CuisinesNotFoundException(String message) {
        super(message);
    }
}
