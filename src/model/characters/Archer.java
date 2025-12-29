package model.characters;

import model.items.Weapon;

import java.util.List;

public class Archer extends Character {
    private int dexterity;

    public Archer(String name, Weapon weapon) {
        // Стартовые статы Лучника: 100 HP, 60 Mana, 60 Gold
        super(name, 100, 60, 60, weapon);
        this.dexterity = 12;
    }

    @Override
    public void onLevelUp() {
        level++;
        maxHealth += 15;
        maxMana += 10;
        this.dexterity += 4;
        health = maxHealth;
        mana = maxMana;
    }

    public int calculateAttackDamage() {
        int weaponDmg = (equippedWeapon != null) ? equippedWeapon.calculateDamage() : 5;
        return weaponDmg + dexterity;
    }

    public void multiShot(List<Attackable> targets) {
        if (mana >= 15) {
            mana -= 15;
            for (Attackable target : targets) {
                target.takeDamage(calculateAttackDamage() / 2);
            }
            System.out.println(name + " выпустил град стрел!");
        }
    }
}
