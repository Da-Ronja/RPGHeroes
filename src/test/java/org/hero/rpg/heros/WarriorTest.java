package org.hero.rpg.heros;

import org.hero.rpg.equipment.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    private Hero warrior;
    private Weapon weapon;
    private Armor armor;

    @BeforeEach
    public void setUp() {
        warrior = new Warrior("Warrior Head");
        weapon = new Weapon("Common Axe", 1, WeaponType.AXE, 2);
        armor = new Armor("Common Plate Chest", 1, Slot.BODY, ArmorType.PLATE,new HeroAttribute(1,0, 0));
    }

    @Test
    void createMageNameShouldBeWarriorHead() {
        String expectedName = "Warrior Head";
        String actualName = warrior.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void createMageNameShouldNotBeFluffy() {
        String expectedName = "Fluffy";
        String actualName = warrior.getName();

        assertNotEquals(expectedName, actualName);
    }

    @Test
    public void testStartingLevelShouldBeOne() {
        int expectedLevel =  1;
        int actualLevel = warrior.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testStartingLevelShouldNotBeFive() {
        int expectedLevel =  5;
        int actualLevel = warrior.getLevel();

        assertNotEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testStartingLevelAttributesShouldBe521() {
        HeroAttribute expectedAttributes = new HeroAttribute(5, 2, 1);
        HeroAttribute actualAttributes = warrior.getStartingLevelAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testStartingLevelAttributesShouldNotBe123() {
        HeroAttribute expectedAttributes = new HeroAttribute(1, 2, 3);
        HeroAttribute actualAttributes = warrior.getStartingLevelAttributes();

        assertNotEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testLevelIncreasedByOneWithLeveUp() {
        int expectedLevel = 2;
        warrior.levelUp();
        int actualLevel = warrior.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testLevelIncreasedByFiveWithLeveUp() {
        int expectedLevel = 5;
        for (int i = 1; i < expectedLevel; i++) {
            warrior.levelUp();
        }
        int actualLevel = warrior.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testAttributesIncreasedByCorrectAmountWhenLeveUpByOne() {
        HeroAttribute expectedAttributes = new HeroAttribute(8, 4, 2);
        warrior.levelUp();
        HeroAttribute actualAttributes = warrior.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testAttributesIncreasedByCorrectAmountWhenLeveUpByFour() {
        HeroAttribute expectedAttributes = new HeroAttribute(17, 10, 5);
        warrior.levelUp();
        warrior.levelUp();
        warrior.levelUp();
        warrior.levelUp();
        HeroAttribute actualAttributes = warrior.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    void testCreateWeaponNameShouldBeCommonAxe() {
        String expectedName = "Common Axe";
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
        WeaponType expectedType = WeaponType.AXE;
        WeaponType actualType = weapon.getWeaponType();

        assertEquals(expectedType, actualType);
    }

    @Test
    void testCreateWeaponDamageShouldBe2() {
        int expectedWeaponDamage = 2;
        int actualWeaponDamage = weapon.getWeaponDamage();

        assertEquals(expectedWeaponDamage, actualWeaponDamage);
    }

    @Test
    void testCreateArmorNameShouldBeCommonPlateChest() {
        String expectedName = "Common Plate Chest";
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
        ArmorType expectedType = ArmorType.PLATE;
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