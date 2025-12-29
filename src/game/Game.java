package game;

import model.Exseptions.InvalidActionException;
import model.characters.*;
import model.items.*;
import model.Exseptions.*;
import utils.GameUtils;
import java.util.*;

public class Game {
    private model.characters.Character player;
    private DungeonMap map;
    private Shop shop;
    private BattleSystem battleSystem;
    private Scanner scanner;
    private boolean running;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.battleSystem = new BattleSystem();
        this.map = new DungeonMap();
        this.shop = new Shop();
        this.running = true;
    }

    public void start() {
        System.out.println("Start");
        createCharacter();
        initializeMap();
        initializeShop();

        while (running && player.isAlive()) {
            showMenu();
        }

        System.out.println("Exit");
    }

    private void createCharacter() {
        System.out.println("בחר סוג דמות: 1. לוחם | 2. קוסם | 3. קשת");
        int choice = scanner.nextInt();
        System.out.println("הכנס שם לדמות:");
        String name = scanner.next();
        System.out.println();

        switch (choice) {
            case 1 -> player = new Warrior(name, new Weapon("Sword", 10, 1, 1, 10));
            case 2 -> player = new Mage(name, new Weapon("Magic sphere", 10, 1, 1, 10));
            case 3 -> player = new Archer(name, new Weapon("Bow", 10, 1, 1, 10));
            default -> {
                System.out.println("בחירה לא חוקית, נוצר לוחם כברירת מחדל.");
                player = new Warrior(name, new Weapon("Sword", 10, 1, 1, 10));
            }
        }
    }

    private void initializeMap() {
        GameLocation start = new GameLocation("כניסה למבוך", 0);
        GameLocation forest = new GameLocation("יער אפל", 1);
        GameLocation cave = new GameLocation("מערת הדרקון", 5);

        map.addLocation(start);
        map.addLocation(forest);
        map.addLocation(cave);

        map.connectLocations("כניסה למבוך", "יער אפל");
        map.connectLocations("יער אפל", "מערת הדרקון");
    }

    private void initializeShop() {
        shop.addItemToShop(new Potion("שיקוי חיים קטן", 10, 1, 20), 5);
        shop.addItemToShop(new Weapon("חרב ברזל", 50, 2, 5, 12), 1);
        shop.addItemToShop(new Armor("שריון עור", 40, 1), 3);
    }

    private void showMenu() {
        System.out.println("\n--- תפריט ראשי ---");
        System.out.println("1. חנות | 2. מעבר חדר | 3. סטטוס דמות | 4. יציאה");
        int choice = scanner.nextInt();

        try {
            switch (choice) {
                case 1 -> handleShop();
                case 2 -> handleMovement();
                case 3 -> showStatus();
                case 4 -> running = false;
            }
        } catch (Exception e) {
            System.err.println("שגיאה: " + e.getMessage());
        }
    }

    private void handleShop() throws InsufficientGoldException, InventoryFullException, ItemNotFoundException {
        System.out.println("פריטים בחנות:");
        List<Item> items = shop.getAvailableItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + ". " + items.get(i).getName() + " - מחיר: " + items.get(i).getBuyPrice());
        }
        System.out.println("בחר מספר פריט לקנייה או -1 ליציאה:");
        int choice = scanner.nextInt();
        if (choice != -1 && choice < items.size()) {
            shop.buyItem(player, items.get(choice));
            System.out.println("תתחדש על ה-" + items.get(choice).getName() + "!");
        }
    }

    private void handleMovement() throws InvalidActionException {
        System.out.println("לאן תרצה ללכת?");
        List<GameLocation> options = map.getAccessibleLocations();
        for (int i = 0; i < options.size(); i++) {
            System.out.println(i + ". " + options.get(i).getName());
        }
        int choice = scanner.nextInt();
        if (choice >= 0 && choice < options.size()) {
            map.moveTo(options.get(choice).getName());
            checkRandomEncounter();
        }
    }

    private void checkRandomEncounter() {
        if (Math.random() < 0.3) {
            System.out.println("מפלצת הופיעה! מתחיל קרב...");
        }
    }

    private void showStatus() {
        System.out.println("שם: " + player.getHealth() + " HP | זהב: " + player.getGold());
        System.out.println("ציוד נוכחי: " + (player.getWeapon() != null ? player.getWeapon().getName() : "אין"));
    }

    public static void main(String[] args) {
        new Game().start();
    }
}