package org.hero.rpg.heros;

import org.hero.rpg.equipment.*;
import org.hero.rpg.exception.InvalidArmorException;
import org.hero.rpg.exception.InvalidWeaponException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    private Hero warrior;
    private Weapon weapon;
    private Weapon weaponInvalid;
    private Armor armor;
    private Armor armorInvalid;

    @BeforeEach
    public void setUp() {
        warrior = new Warrior("Warrior Head");
        weapon = new Weapon("Common Axe", 1, WeaponType.AXE, 2);
        armor = new Armor("Common Plate Chest", 1, Slot.BODY, ArmorType.PLATE, new HeroAttribute(1, 0, 0));
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
        int expectedLevel = 1;
        int actualLevel = warrior.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testStartingLevelShouldNotBeFive() {
        int expectedLevel = 5;
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
        HeroAttribute expectedAttribute = new HeroAttribute(1, 0, 0);
        HeroAttribute actualAttribute = armor.getArmorAttribute();

        assertEquals(expectedAttribute, actualAttribute);
    }

    // A Hero should be able to equip a Weapon, the appropriate exceptions should be thrown if invalid (level requirement and type)
    @Test
    void testEquipWeaponDoesNotThrowsExceptions() {

        assertDoesNotThrow(() -> warrior.equip(weapon));

        assertEquals(weapon.getRequiredLevel(), warrior.getLevel());
    }

    @Test
    void testEquipWeaponRequiredLevelThrowsExceptions() {
        weaponInvalid = new Weapon("Common Axe", 3, WeaponType.AXE, 2);

        Exception exception = assertThrows(InvalidWeaponException.class, () -> warrior.equip(weaponInvalid));

        String expectedMessage = "This hero is not high enough level to equip " + weaponInvalid.getWeaponType();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEquipWeaponTypeThrowsExceptions() {
        weaponInvalid = new Weapon("Common Axe", 1, WeaponType.WAND, 2);

        Exception exception = assertThrows(InvalidWeaponException.class, () -> warrior.equip(weaponInvalid));

        String expectedMessage = "This hero cannot equip weapons of type " + weaponInvalid.getWeaponType();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // A Hero should be able to equip Armor, the appropriate exceptions should be thrown if invalid (level requirement and type)
    @Test
    void testEquipArmorDoesNotThrowsExceptions() {

        assertDoesNotThrow(() -> warrior.equip(armor));

        assertEquals(armor.getRequiredLevel(), warrior.getLevel());
    }

    @Test
    void testEquipArmorTypeThrowsExceptions() {
        armorInvalid = new Armor("Common Plate Chest", 1, Slot.BODY, ArmorType.CLOTH, new HeroAttribute(1, 0, 0));

        Exception exception = assertThrows(InvalidArmorException.class, () -> warrior.equip(armorInvalid));

        String expectedMessage = "This hero cannot equip armor of type " + armorInvalid.getArmorType();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEquipArmorRequiredLevelThrowsExceptions() {
        armorInvalid = new Armor("Common Plate Chest", 2, Slot.BODY, ArmorType.PLATE, new HeroAttribute(1, 0, 0));

        Exception exception = assertThrows(InvalidArmorException.class, () -> warrior.equip(armorInvalid));

        String expectedMessage = "This hero is not high enough level to equip this armor.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Total attributes should be calculated correctly
    // With no equipment
    @Test
    void testCalculateTotalAttributesNoEquipment() {
        HeroAttribute expectedAttributes = new HeroAttribute(5, 2, 1);
        HeroAttribute actualAttributes = warrior.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    // With one piece of armor
    @Test
    void testCalculateTotalAttributesOnePieceOfArmor() throws InvalidArmorException {
        warrior.equip(armor);
        HeroAttribute expectedAttributes = new HeroAttribute(6, 2, 1);
        HeroAttribute actualAttributes = warrior.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    // With two pieces of armor
    @Test
    void testCalculateTotalAttributesTwoPiecesOfArmor() throws InvalidArmorException {
        Armor armor2 = new Armor("Common Plate Legs", 1, Slot.LEGS, ArmorType.PLATE, new HeroAttribute(1, 0, 1));

        warrior.equip(armor);
        warrior.equip(armor2);

        HeroAttribute expectedAttributes = new HeroAttribute(7, 2, 2);
        HeroAttribute actualAttributes = warrior.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }
    // With a replaced piece of armor (equip armor, then equip new armor in the same slot)
    @Test
    void testCalculateTotalAttributesReplacedPieceOfArmor() throws InvalidArmorException {
        Armor armor2 = new Armor("Uncommon Plate Chest", 1, Slot.BODY, ArmorType.PLATE, new HeroAttribute(1, 0, 1));
        warrior.equip(armor);
        warrior.equip(armor2);

        HeroAttribute expected = new HeroAttribute(6, 2, 2);
        HeroAttribute actual = warrior.calculateTotalAttributes();

        assertEquals(expected, actual);
    }

}