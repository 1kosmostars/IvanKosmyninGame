package model.Exseptions;

public class InsufficientGoldException extends Exception {
    public InsufficientGoldException(double required, double current) {
        super("Недостаточно золота! Нужно: " + required + ", у вас есть: " + current);
    }


}