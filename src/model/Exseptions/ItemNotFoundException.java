package model.Exseptions;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String itemName) {
        super("Предмет '" + itemName + "' не найден.");
    }
}