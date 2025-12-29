package model.items;

public class Armor extends Item {
    private int defenseValue;

    public Armor(String name, double basePrice, int rarity) {
        super(name, basePrice, rarity);
    }

    public int reduceDamage(int incomingDamage) {
        int reduced = incomingDamage - defenseValue;
        return Math.max(reduced, 0);
    }

    public int getDefenseValue() {
        return this.defenseValue;
    }
}
