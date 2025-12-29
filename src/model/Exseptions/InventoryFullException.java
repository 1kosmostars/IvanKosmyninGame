package model.Exseptions;

public class InventoryFullException extends Exception {
    public InventoryFullException() {
        super("Инвентарь заполнен! Вы не можете нести больше предметов.");
    }
}

