package org.hero.rpg.heros;

import org.hero.rpg.equipment.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MageTest {

    private Hero mage;
    private Weapon weapon;
    private Armor armor;

    @BeforeEach // Same
    public void setUp() {
        mage = new Mage("TinTin");
        weapon = new Weapon("Common Wand", 1, WeaponType.WAND, 2);
        armor = new Armor("Common Cloth Chest", 1, Slot.BODY, ArmorType.CLOTH, new HeroAttribute(1,0, 0));
    }

    @Test
        // Same
    void createMageNameShouldBeTinTin() {
        String expectedName = "TinTin";
        String actualName = mage.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void createMageNameShouldNotBeFluffy() {
        String expectedName = "Fluffy";
        String actualName = mage.getName();

        assertNotEquals(expectedName, actualName);
    }

    @Test
    public void testStartingLevelShouldBeOne() {
        int expectedLevel = 1;
        int actualLevel = mage.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testStartingLevelShouldNotBeFive() {
        int expectedLevel = 5;
        int actualLevel = mage.getLevel();

        assertNotEquals(expectedLevel, actualLevel);
    }

    @Test // Same
    public void testStartingLevelAttributesShouldBe118() {
        HeroAttribute expectedAttributes = new HeroAttribute(1, 1, 8);
        HeroAttribute actualAttributes = mage.getStartingLevelAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test // Same
    public void testStartingLevelAttributesShouldNotBe118() {
        HeroAttribute expectedAttributes = new HeroAttribute(1, 2, 8);
        HeroAttribute actualAttributes = mage.getStartingLevelAttributes();

        assertNotEquals(expectedAttributes, actualAttributes);
    }

    @Test // Same
    public void testLevelIncreasedByOneWithLeveUp() {
        int expectedLevel = 2;
        mage.levelUp();
        int actualLevel = mage.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testLevelIncreasedByFiveWithLeveUp() {
        // level setup
        int expectedLevel = 5;

        for (int i = 1; i < expectedLevel; i++) {
            mage.levelUp();
        }

        int actualLevel = mage.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test // Same
    public void testAttributesIncreasedByCorrectAmountWhenLeveUpByOne() {
        HeroAttribute expectedAttributes = new HeroAttribute(2, 2, 13);
        mage.levelUp();
        HeroAttribute actualAttributes = mage.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testAttributesIncreasedByCorrectAmountWhenLeveUpByFour() {
        HeroAttribute expectedAttributes = new HeroAttribute(5, 5, 28);
        mage.levelUp();
        mage.levelUp();
        mage.levelUp();
        mage.levelUp();
        HeroAttribute actualAttributes = mage.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    // When Weapon is created, it needs to have the correct name, required level, slot, weapon type, and damage
    @Test
    void testCreateWeaponNameShouldBeCommonAxe() {
        String expectedName = "Common Wand";
        String actualName = weapon.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void testCreateWeaponRequiredLevelShouldBe1() {
        int expectedRequiredLevel = 1;
        int actualRequiredLevel = weapon.getRequiredLevel();

        assertEquals(expectedRequiredLevel, actualRequiredLevel);
    }

    @Test
    void testCreateWeaponSlotShouldBeWeapon() {
        Slot expectedSlot = Slot.WEAPON;
        Slot actualSlot = weapon.getSlot();

        assertEquals(expectedSlot, actualSlot);
    }

    @Test
    void testCreateWeaponTypeShouldBeAxe() {
        WeaponType expectedType = WeaponType.WAND;
        WeaponType actualType = weapon.getWeaponType();

        assertEquals(expectedType, actualType);
    }

    @Test
    void testCreateWeaponDamageShouldBe2() {
        int expectedWeaponDamage = 2;
        int actualWeaponDamage = weapon.getWeaponDamage();

        assertEquals(expectedWeaponDamage, actualWeaponDamage);
    }

    // When Armor is created, it needs to have the correct name, required level, slot, armor type, and armor attributes
    @Test
    void testCreateArmorNameShouldBeCommonPlateChest() {
        String expectedName = "Common Cloth Chest";
        String actualName = armor.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void testCreateArmorRequiredLevelShouldBe1() {
        int expectedRequiredLevel = 1;
        int actualRequiredLevel = armor.getRequiredLevel();

        assertEquals(expectedRequiredLevel, actualRequiredLevel);
    }

    @Test
    void testCreateArmorSlotShouldBeBody() {
        Slot expectedSlot = Slot.BODY;
        Slot actualSlot = armor.getSlot();

        assertEquals(expectedSlot, actualSlot);
    }

    @Test
    void testCreateArmorTypeShouldBePlate() {
        ArmorType expectedType = ArmorType.CLOTH;
        ArmorType actualType = armor.getArmorType();

        assertEquals(expectedType, actualType);
    }

    @Test
    void testCreateArmorAttributeShouldBePlate() {
        HeroAttribute expectedAttribute = new HeroAttribute(1,0, 0);
        HeroAttribute actualAttribute = armor.getArmorAttribute();

        assertEquals(expectedAttribute, actualAttribute);
    }

}