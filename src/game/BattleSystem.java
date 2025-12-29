package game;

import model.characters.Character;
import model.characters.Attackable;
import utils.ActionFilter;
import utils.GameUtils;
import java.util.*;

public class BattleSystem {
    private Queue<BattleAction> actionQueue = new LinkedList<>();


    public void queueAction(BattleAction action) {
        actionQueue.add(action);
    }


    public void queuePlayerAction(Character player, Attackable target) {
        queueAction(new BattleAction(10, "שחקן תוקף") {
            @Override
            public void execute() {
                executeAttack(player, target);
            }
        });
    }


    public void generateEnemyAction(Character enemy, Character player) {
        queueAction(new BattleAction(5, "אויב פועל") {
            @Override
            public void execute() {
                if (Math.random() > 0.2) {
                    executeAttack(enemy, player);
                } else {
                    System.out.println(enemy.getName() + " מנסה להשתמש ביכולת מיוחדת!");
                    executeSpecialAbility(enemy, player);
                }
            }
        });
    }


    public void sortActionsByPriority() {
        List<BattleAction> list = new ArrayList<>(actionQueue);
        list.sort(new Comparator<BattleAction>() {
            @Override
            public int compare(BattleAction a1, BattleAction a2) {
                // מיון מהגבוה לנמוך (עדיפות גבוהה קודם)
                return Integer.compare(a2.getPriority(), a1.getPriority());
            }
        });
        actionQueue.clear();
        actionQueue.addAll(list);
    }


    public void processNextAction() {
        BattleAction action = actionQueue.poll();
        if (action != null) {
            System.out.println("מבצע פעולה: " + action.getDescription());
            action.execute();
        }
    }


    public void processAllActions() {
        sortActionsByPriority();
        while (!actionQueue.isEmpty()) {
            processNextAction();
        }
    }

    public void executeAttack(Character attacker, Attackable target) {
        int damage = attacker.getWeapon().calculateDamage();
        target.takeDamage(damage);
        System.out.println(attacker.getName() + " גרם ל-" + damage + " נזק!");
    }


    public void executeSpecialAbility(Character attacker, Attackable target) {
        System.out.println(attacker.getName() + " הפעיל יכולת מיוחדת!");
    }


    public void executeFlee(Character runner) {
        if (Math.random() > 0.5) {
            System.out.println(runner.getName() + " הצליח לברוח מהקרב!");
            actionQueue.clear();
        } else {
            System.out.println(runner.getName() + " נכשל בבריחה!");
        }
    }


    public List<BattleAction> getActionsFilteredBy(ActionFilter filter) {
        List<BattleAction> filtered = new ArrayList<>();
        for (BattleAction action : actionQueue) {
            if (filter.filter(action)) {
                filtered.add(action);
            }
        }
        return filtered;
    }
}
