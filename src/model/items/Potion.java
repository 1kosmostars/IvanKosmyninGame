package model.items;
import model.characters.Character;
public class Potion extends Item implements Usable {
    private int power;
    private boolean used = false;
    public Potion(String name, double price, int rarity, int power) {
        super(name, price, rarity); this.power = power;
    }
    @Override public void use(Character target) { target.heal(power); used = true; }
    @Override public boolean canUse(Character target) { return !used; }
    @Override public boolean isSellable() { return !used; }
}