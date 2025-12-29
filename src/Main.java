
import game.*;
import model.Exseptions.InvalidActionException;
import model.characters.*;
import model.characters.Character;
import model.items.*;
import model.Exseptions.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(" Ivan Kosmynin");

        try {

            Map<String, Character> partyMap = new HashMap<>();
            List<Item> marketArrayList = new ArrayList<>();
            LinkedList<String> eventHistoryLog = new LinkedList<>();
            Map<String, GameLocation> locationMap = new HashMap<>();


            Weapon starterSword = new Weapon("Rusty sword", 20.0, 1, 5, 8);
            Character hero = new Warrior("Aragorn", starterSword);
            partyMap.put(hero.getName(), hero);

            System.out.println("[Hero] Created: " + hero.getName() + " | HP: " + hero.getHealth());
            System.out.println("[Hero] Weapon: " + hero.getWeapon().getName());


            System.out.println("\n--- Initializing Shop ---");
            Shop dungeonShop = new Shop();
            marketArrayList.add(new Potion("Heal Potion", 8.0, 1, 30));

            Item potionFromMarket = marketArrayList.get(0);
            dungeonShop.addItemToShop(potionFromMarket, 3);

            System.out.println("[Shop] Purchasing " + potionFromMarket.getName() + "...");
            dungeonShop.buyItem(partyMap.get("Aragorn"), potionFromMarket);

            eventHistoryLog.add("Aragorn purchased Heal Potion");
            System.out.println("[Shop] Transaction successful. Gold remaining: " + hero.getGold());


            System.out.println("\n--- Initializing Battle ---");
            BattleSystem battle = new BattleSystem();

            Weapon orcClaws = new Weapon("Orc Claws", 0, 1, 4, 6);
            Character enemy = new Warrior("Orc Scout", orcClaws);
            partyMap.put(enemy.getName(), enemy);

            // Queue actions for the round
            battle.queuePlayerAction(partyMap.get("Aragorn"), partyMap.get("Orc Scout"));
            battle.generateEnemyAction(partyMap.get("Orc Scout"), partyMap.get("Aragorn"));

            System.out.println("[Battle] Processing turn queue...");
            battle.processAllActions();
            eventHistoryLog.add("Round processed via BattleSystem Queue");

            System.out.println("[Battle] Result: " + hero.getName() + " (" + hero.getHealth() + " HP) vs "
                    + enemy.getName() + " (" + enemy.getHealth() + " HP)");


            System.out.println("\n--- Navigating World ---");
            DungeonMap worldMap = new DungeonMap();

            locationMap.put("Entrance", new GameLocation("Entrance", 0));
            locationMap.put("Dark Forest", new GameLocation("Dark Forest", 2));

            worldMap.addLocation(locationMap.get("Entrance"));
            worldMap.addLocation(locationMap.get("Dark Forest"));
            worldMap.connectLocations("Entrance", "Dark Forest");

            worldMap.moveTo("Dark Forest");
            eventHistoryLog.add("Hero moved to Dark Forest");


            System.out.println("\n--- Event History (LinkedList) ---");
            for (String log : eventHistoryLog) {
                System.out.println(">> " + log);
            }

        } catch (InsufficientGoldException e) {
            System.err.println("[Gold Error]: " + e.getMessage());
        } catch (InventoryFullException e) {
            System.err.println("[Inventory Error]: " + e.getMessage());
        } catch (ItemNotFoundException e) {
            System.err.println("[Item Error]: " + e.getMessage());
        } catch (InvalidActionException e) {
            System.err.println("[Navigation Error]: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[Fatal Error]: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== Test Sequence Completed ===");
    }
}
