package utils;

import model.items.Item;
import model.characters.Character;
import java.util.*;

public class GameUtils {

    // 1. מיון פריטים לפי מחיר (מהזול ליקר) באמצעות מחלקה אנונימית
    public static void sortItemsByPrice(List<Item> items) {
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return Double.compare(i1.getBuyPrice(), i2.getBuyPrice());
            }
        });
    }

    // 2. מיון פריטים לפי נדירות (מהגבוה לנמוך)
    public static void sortItemsByRarity(List<Item> items) {
        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return Integer.compare(i2.getRarity(), i1.getRarity());
            }
        });
    }

    // 3. מיון דמויות לפי כמות חיים (HP)
    public static void sortCharactersByHealth(List<Character> characters) {
        characters.sort(new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return Integer.compare(c1.getHealth(), c2.getHealth());
            }
        });
    }

    // 4. סינון פריטים לפי תקציב השחקן - שימוש במחלקה אנונימית בתוך המתודה
    public static List<Item> filterAffordableItems(List<Item> items, final double gold) {
        return filterItems(items, new ItemFilter() {
            @Override
            public boolean filter(Item item) {
                return item.getBuyPrice() <= gold;
            }
        });
    }

    // 5. סינון לפי נדירות מינימלית
    public static List<Item> filterByRarity(List<Item> items, final int minRarity) {
        return filterItems(items, new ItemFilter() {
            @Override
            public boolean filter(Item item) {
                return item.getRarity() >= minRarity;
            }
        });
    }

    // 6. מתודת עזר כללית לסינון (מקבלת ממשק פונקציונלי)
    public static List<Item> filterItems(List<Item> items, ItemFilter condition) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (condition.filter(item)) {
                result.add(item);
            }
        }
        return result;
    }

    // 7. מציאת הפריט ה"טוב" ביותר (לפי מחיר גבוה למשל)
    public static Item findBestItem(List<Item> items) {
        if (items == null || items.isEmpty()) return null;

        Item best = items.get(0);
        for (Item current : items) {
            if (current.getBuyPrice() > best.getBuyPrice()) {
                best = current;
            }
        }
        return best;
    }
}
