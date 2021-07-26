package exceptions;


public class EntityNotFoundException extends Exception {
    private final String item;

    public EntityNotFoundException(String item) {
        this.item = item;
    }

    public String getMessage() {
        return "Sorry " + item + " not found";
    }
}
