package org.hero.rpg.equipment;

import org.hero.rpg.heros.HeroAttribute;

public class Armor extends Item {

    ArmorType armorType;
    HeroAttribute armorAttribute;

    public Armor(String name, int requiredLevel, Slot slot, ArmorType armorType, HeroAttribute armorAttribute) {
        super(name, requiredLevel, slot);
        this.armorType = armorType;
        this.armorAttribute = armorAttribute;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public Slot getSlot() {
        return slot;
    }

    public HeroAttribute getArmorAttribute() {
        return armorAttribute;
    }
}
