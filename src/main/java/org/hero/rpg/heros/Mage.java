package org.hero.rpg.heros;

import org.hero.rpg.equipment.*;

import java.util.EnumSet;

public class Mage extends Hero {
    public Mage(String name) {
        super(name, EnumSet.of(WeaponType.STAFF, WeaponType.WAND), EnumSet.of(ArmorType.CLOTH));
    }

    @Override
    protected HeroAttribute getStartingLevelAttributes() {
        return new HeroAttribute(1, 1, 8);
    }

    @Override
    protected HeroAttribute getLevelAttributeGain() {
        return new HeroAttribute(1, 1, 5);
    }

    @Override
    public double calculateDamage() {
        double baseDamage = 0.0;

        // Get the equipped weapon, if any
        Weapon weapon = (Weapon) equipment.get(Slot.WEAPON);

        if (weapon != null) {
            baseDamage = weapon.getWeaponDamage();
        } else {
            // If no weapon is equipped, set base damage to 1
            baseDamage = 1.0;
        }

        // Calculate the damaging attribute based on hero class
        double damagingAttribute = calculateTotalAttributes().getIntelligence();

        return baseDamage * (1.0 + damagingAttribute / 100.0);
    }
}

