package model.characters;

import model.Exseptions.InventoryFullException;
import model.Exseptions.ItemNotFoundException;
import model.items.Armor;
import model.items.Item;
import model.items.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Character implements Attackable {
    protected int maxMana;
    protected int mana;
    protected int experience;
    protected String name;
    protected int health, maxHealth, gold, level = 1;
    protected List<Item> inventory = new ArrayList<>();
    protected Map<String, Armor> equippedArmor = new HashMap<>();
    protected Weapon equippedWeapon;

    public Character(String name, int health, int mana, int gold, Weapon weapon) {
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.maxMana = mana;
        this.mana = mana;
        this.gold = gold;
        this.level = 1;
        this.experience = 0;
        this.inventory = new ArrayList<>();
        this.equippedArmor = new HashMap<>(); // slots: "head", "chest", "legs"
        this.equippedWeapon = weapon;
    }

    public void addItem(Item i) throws InventoryFullException {
        if (inventory.size() >= 10) throw new InventoryFullException();
        inventory.add(i);
    }

    @Override public void takeDamage(int dmg) {
        int finalDmg = Math.max(dmg - getTotalDefense(), 0);
        health -= finalDmg;
    }

    public int getTotalDefense() {
        return equippedArmor.values().stream().mapToInt(Armor::getDefenseValue).sum();
    }

    public void heal(int amount) { health = Math.min(health + amount, maxHealth); }
    @Override
    public boolean isAlive() { return health > 0; }
    @Override
    public int getHealth() { return health; }

    public abstract void onLevelUp();
    public String getName()
    {
        return this.name;
    }
    public Weapon getWeapon()
    {
        return this.equippedWeapon;
    }
    public int getGold()
    {
        return this.gold;
    }

    public void spendGold(int buyPrice)
    {
        this.gold -= buyPrice;
    }
}

