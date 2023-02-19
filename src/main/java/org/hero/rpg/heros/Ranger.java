package org.hero.rpg.heros;

import org.hero.rpg.equipment.ArmorType;
import org.hero.rpg.equipment.Slot;
import org.hero.rpg.equipment.Weapon;
import org.hero.rpg.equipment.WeaponType;

import java.util.EnumSet;

public class Ranger extends Hero {

    public Ranger(String name) {
        super(name, EnumSet.of(WeaponType.BOW), EnumSet.of(ArmorType.LEATHER, ArmorType.MAIL));
    }

    @Override
    protected HeroAttribute getStartingLevelAttributes() {
        return new HeroAttribute(1, 7, 1);
    }

    @Override
    protected HeroAttribute getLevelAttributeGain() {
        return new HeroAttribute(1, 5, 1);
    }

    @Override
    public double calculateDamage() {
        double baseDamage;

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
