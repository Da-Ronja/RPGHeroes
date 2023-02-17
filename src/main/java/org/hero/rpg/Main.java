package org.hero.rpg;

import org.hero.rpg.equipment.*;
import org.hero.rpg.exception.InvalidArmorException;
import org.hero.rpg.exception.InvalidWeaponException;
import org.hero.rpg.heros.Hero;
import org.hero.rpg.heros.HeroAttribute;
import org.hero.rpg.heros.Mage;

public class Main {
    public static void main(String[] args) {
        System.out.println("Assignment\n" +
                "Build a console application in Java");

        Hero mage = new Mage("TinTin");
        System.out.println(mage);

        mage.levelUp();
        System.out.println(mage);

        mage.levelUp();
        System.out.println(mage);


        Weapon sword = new Weapon("Sword of Fire", 1, WeaponType.STAFF, 20);
        try {
            mage.equip(sword);
        } catch (InvalidWeaponException e) {
            throw new RuntimeException(e);
        }

        mage.levelUp();
        System.out.println(mage);
        mage.levelUp();
        System.out.println(mage);

        Armor armor = new Armor("Shield", 1, Slot.HEAD, ArmorType.CLOTH, new HeroAttribute(0, 5, 0));

        try {
            mage.equip(armor);
        } catch (InvalidArmorException e) {
            throw new RuntimeException(e);
        }

        System.out.println(mage);
        mage.calculateTotalAttributes();

        System.out.println(mage);
        System.out.println(mage.calculateTotalAttributes());
        System.out.println(mage.display());



    }
}