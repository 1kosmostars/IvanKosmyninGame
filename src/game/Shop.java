package game;

import model.Exseptions.ItemNotFoundException;
import model.items.*;
import model.characters.Character;
import model.Exseptions.*;
import java.util.*;

public class Shop {
    private Map<Item, Integer> stock = new HashMap<>();

    public void addItemToShop(Item item, int quantity) {
        stock.put(item, stock.getOrDefault(item, 0) + quantity);
    }

    public void buyItem(Character buyer, Item item) throws InsufficientGoldException, InventoryFullException, ItemNotFoundException {
        if (!stock.containsKey(item) || stock.get(item) <= 0) {
            throw new ItemNotFoundException(item.getName());
        }
        if (buyer.getGold() < item.getBuyPrice()) {
            throw new InsufficientGoldException(item.getBuyPrice(), buyer.getGold());
        }

        buyer.addItem(item);
        buyer.spendGold((int)item.getBuyPrice());
        stock.put(item, stock.get(item) - 1);
    }

    public List<Item> getAvailableItems() {
        List<Item> available = new ArrayList<>();
        for (var entry : stock.entrySet()) {
            if (entry.getValue() > 0) available.add(entry.getKey());
        }
        return available;
    }
}
