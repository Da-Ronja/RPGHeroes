package org.hero.rpg.heros;

import org.hero.rpg.equipment.*;
import org.hero.rpg.exception.InvalidArmorException;
import org.hero.rpg.exception.InvalidWeaponException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Hero {

    private final String name;
    private int level;
    protected HeroAttribute levelAttributes;
    protected final Map<Slot, Item> equipment;
    private final Set<WeaponType> validWeaponTypes;
    private final Set<ArmorType> validArmorTypes;

    public Hero(String name, Set<WeaponType> validWeaponTypes, Set<ArmorType> validArmorTypes) {
        this.name = name;
        this.level = 1;
        this.levelAttributes = getStartingLevelAttributes();
        this.equipment = initializeEquipment();
        this.validWeaponTypes = validWeaponTypes;
        this.validArmorTypes = validArmorTypes;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        this.level += 1;
        this.levelAttributes = this.levelAttributes.plus(getLevelAttributeGain());
    }

    public void equip(Weapon weapon) throws InvalidWeaponException {
        if (!this.validWeaponTypes.contains(weapon.getWeaponType())) {
            throw new InvalidWeaponException("This hero cannot equip weapons of type " + weapon.getWeaponType());
        }

        if (weapon.getRequiredLevel() > getLevel()) {
            throw new InvalidWeaponException("This hero is not high enough level to equip " + weapon.getWeaponType());
        }

        this.equipment.put(weapon.getSlot(), weapon);
    }

    public void equip(Armor armor) throws InvalidArmorException {
        if (!this.validArmorTypes.contains(armor.getArmorType())) {
            throw new InvalidArmorException("This hero cannot equip armor of type " + armor.getArmorType());
        }

        if (armor.getRequiredLevel() > getLevel()) {
            throw new InvalidArmorException("This hero is not high enough level to equip this armor.");
        }

        // Replace the current armor in the corresponding equipment slot with the new armor
        this.equipment.put(armor.getSlot(), armor);
        this.levelAttributes = this.levelAttributes.plus(armor.getArmorAttribute());
    }

    public HeroAttribute totalAttributes() {
        return calculateTotalAttributes();
    }

    @Override
    public String toString() {
        return "Hero: " + this.getClass().getSimpleName() +"{" +
                "name= " + name + '\'' +
                ", level= " + level +
                ", levelAttributes=" + levelAttributes +
                ", equipment=" + equipment +
                ", validWeaponTypes=" + validWeaponTypes +
                ", validArmorTypes=" + validArmorTypes +
                '}';
    }

    public String display() {
        HeroAttribute totalAttributes = calculateTotalAttributes();
        double totalDamage = calculateDamage();
        String className = this.getClass().getSimpleName();

        return  "Name: " + this.name + "\n" +
                "Class: " + className + "\n" +
                "Level: " + this.level + "\n" +
                "Total strength: " + totalAttributes.getStrength() + "\n" +
                "Total dexterity: " + totalAttributes.getDexterity() + "\n" +
                "Total intelligence: " + totalAttributes.getIntelligence() + "\n" +
                "Damage: " + totalDamage + "\n";

    }

    // abstract methods to be implemented by each subclass
    protected abstract HeroAttribute getStartingLevelAttributes();

    protected abstract HeroAttribute getLevelAttributeGain();
    protected abstract double calculateDamage();

    public HeroAttribute calculateTotalAttributes() {
        HeroAttribute total = new HeroAttribute(levelAttributes.getStrength(),
                levelAttributes.getDexterity(),
                levelAttributes.getIntelligence());

        for (Item item : equipment.values()) {
            if (item instanceof Armor) {
                total = total.plus(((Armor) item).getArmorAttribute());
            }
        }

        return total;
    }

    // private methods
    private Map<Slot, Item> initializeEquipment() {
        Map<Slot, Item> equipment = new HashMap<>();
        equipment.put(Slot.WEAPON, null);
        equipment.put(Slot.HEAD, null);
        equipment.put(Slot.BODY, null);
        equipment.put(Slot.LEGS, null);
        return equipment;
    }

}
