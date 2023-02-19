package org.hero.rpg.heros;

import org.hero.rpg.equipment.*;
import org.hero.rpg.exception.InvalidArmorException;
import org.hero.rpg.exception.InvalidWeaponException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Hero {
    protected final Map<Slot, Item> equipment;
    private final String name;
    private final Set<WeaponType> validWeaponTypes;
    private final Set<ArmorType> validArmorTypes;
    protected HeroAttribute levelAttributes;
    private int level;

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

        this.equipment.put(armor.getSlot(), armor);
    }

    public HeroAttribute totalAttributes() {
        return calculateTotalAttributes();
    }

    public String display() {
        HeroAttribute totalAttributes = calculateTotalAttributes();
        double totalDamage = calculateDamage();
        String className = this.getClass().getSimpleName();

        return "Name: " + this.name + "\n" + "Class: " + className + "\n" + "Level: " + this.level + "\n" + "Total strength: " + totalAttributes.getStrength() + "\n" + "Total dexterity: " + totalAttributes.getDexterity() + "\n" + "Total intelligence: " + totalAttributes.getIntelligence() + "\n" + "Damage: " + totalDamage + "\n";
    }

    protected abstract HeroAttribute getStartingLevelAttributes();

    protected abstract HeroAttribute getLevelAttributeGain();

    protected abstract double calculateDamage();

    public HeroAttribute calculateTotalAttributes() {
        HeroAttribute total = new HeroAttribute(levelAttributes.getStrength(), levelAttributes.getDexterity(), levelAttributes.getIntelligence());

        for (Item item : equipment.values()) {
            if (item instanceof Armor) {
                total = total.plus(((Armor) item).getArmorAttribute());
            }
        }

        return total;
    }

    private Map<Slot, Item> initializeEquipment() {
        Map<Slot, Item> equipment = new HashMap<>();
        equipment.put(Slot.WEAPON, null);
        equipment.put(Slot.HEAD, null);
        equipment.put(Slot.BODY, null);
        equipment.put(Slot.LEGS, null);
        return equipment;
    }

    @Override
    public String toString() {
        return "Hero: " + this.getClass().getSimpleName() + "{" + "name= " + name + '\'' + ", level= " + level + ", levelAttributes=" + levelAttributes + ", equipment=" + equipment + ", validWeaponTypes=" + validWeaponTypes + ", validArmorTypes=" + validArmorTypes + '}';
    }

}
