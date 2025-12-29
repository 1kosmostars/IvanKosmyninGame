package model.items;
public abstract class Item implements Tradeable {
    protected String name;
    protected double basePrice;
    protected int rarity;

    public Item(String name, double basePrice, int rarity) {
        this.name = name; this.basePrice = basePrice; this.rarity = rarity;
    }

    @Override public double getBuyPrice() { return basePrice * (1 + (rarity * 0.2)); }
    @Override public double getSellPrice() { return getBuyPrice() * 0.5; }
    @Override public boolean isSellable() { return true; }
    public String getName() { return name; }
    public int getRarity() { return rarity; }
}