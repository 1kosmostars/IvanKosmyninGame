package model.characters;

import model.items.Weapon;



public class Warrior extends Character {
    private int meleeDamage;
    public Warrior(String name, Weapon weapon) {
        super(name, 200, 10, 50, weapon);
        this.meleeDamage = 10;
    }


    @Override public void onLevelUp() { level++;
        maxHealth += 10;
        maxMana += 30;
        this.meleeDamage += 10;
        health = maxHealth;
        mana = maxMana;
        System.out.println(name); }
    public void berserk(Attackable target)
    {
        int damage = meleeDamage* equippedWeapon.calculateDamage();
        target.takeDamage(damage);
        mana--;
    }
}