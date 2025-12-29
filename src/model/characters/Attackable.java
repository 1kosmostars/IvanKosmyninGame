package model.characters;

public interface Attackable {


    void takeDamage(int damage);


    boolean isAlive();


    int getHealth();
}
