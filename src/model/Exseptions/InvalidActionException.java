package model.Exseptions;

public class InvalidActionException extends Exception {
    public InvalidActionException(String message) {
        super("Недопустимое действие: " + message);
    }
}

