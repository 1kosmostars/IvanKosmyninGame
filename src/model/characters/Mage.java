package model.characters;

import model.items.Weapon;

public class Mage extends Character {
    private int spellPower;
    private int maxMana;

    public Mage(String name, Weapon weapon) {
        super(name, 70, 150, 50, weapon);
        this.spellPower = 15;
    }

    @Override
    public void onLevelUp() {
        level++;
        maxHealth += 10;
        maxMana += 30;
        spellPower += 5;
        health = maxHealth;
        mana = maxMana;
        System.out.println(name + " поднял уровень! Теперь мана: " + maxMana);
    }

    public int calculateAttackDamage() {
        // Урон мага зависит от посоха и силы заклинаний
        int baseDmg = (equippedWeapon != null) ? equippedWeapon.calculateDamage() : 2;
        return baseDmg + (spellPower / 2);
    }

    public void castFireball(Attackable target) {
        if (mana >= 20) {
            mana -= 20;
            int damage = spellPower * 2;
            target.takeDamage(damage);
            System.out.println(name + " выпустил Огненный шар на " + damage + " урона!");
        }
    }
}
