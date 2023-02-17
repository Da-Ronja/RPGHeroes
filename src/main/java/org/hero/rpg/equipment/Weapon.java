package org.hero.rpg.equipment;

public class Weapon extends Item {

    WeaponType weaponType;
    int weaponDamage;

    public Weapon(String name, int requiredLevel, WeaponType weaponType, int weaponDamage) {
        super(name, requiredLevel, Slot.WEAPON);
        this.weaponType = weaponType;
        this.weaponDamage = weaponDamage;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    // TODO Remove
    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

}
