package model.items;

import model.characters.Character;

public interface Usable {

    void use(Character target);


    boolean canUse(Character target);
}
