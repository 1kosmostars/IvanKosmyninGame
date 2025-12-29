package model.items;

public class Weapon extends Item
{

    private int minDmg, maxDmg;

    public Weapon(String name, double price, int rarity, int min, int max) {
        super(name, price, rarity);
        this.minDmg = min;
        this.maxDmg = max;
    }
    public int calculateDamage()
    {
        return (int)(Math.random() * (maxDmg - minDmg + 1)) + minDmg;
    }
    public double getAverageDamage()
    {
        return (((double) minDmg +maxDmg)/2);
    }
}