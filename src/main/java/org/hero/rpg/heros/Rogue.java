package org.hero.rpg.heros;

import org.hero.rpg.equipment.*;

import java.util.EnumSet;

public class Rogue extends Hero {

    public Rogue(String name) {
        super(name, EnumSet.of(WeaponType.DAGGER, WeaponType.SWORD), EnumSet.of(ArmorType.LEATHER,ArmorType.MAIL));
    }

    @Override
    protected HeroAttribute getStartingLevelAttributes() {
        return new HeroAttribute(2, 6, 1);
    }

    @Override
    protected HeroAttribute getLevelAttributeGain() {
        return new HeroAttribute(1, 4, 1);
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
        double damagingAttribute = calculateTotalAttributes().getDexterity();

        return baseDamage * (1.0 + damagingAttribute / 100.0);
    }

}
