package org.hero.rpg.heros;

import org.hero.rpg.equipment.*;
import org.hero.rpg.exception.InvalidArmorException;
import org.hero.rpg.exception.InvalidWeaponException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {

    private Hero mage;
    private Weapon weapon;
    private Weapon weaponInvalid;
    private Armor armor;
    private Armor armorInvalid;
    @BeforeEach // Same
    public void setUp() {
        mage = new Mage("TinTin");
        weapon = new Weapon("Common Wand", 1, WeaponType.WAND, 2);
        armor = new Armor("Common Cloth Chest", 1, Slot.BODY, ArmorType.CLOTH, new HeroAttribute(1, 0, 0));
    }

    @Test
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

    @Test
    public void testStartingLevelAttributesShouldBe118() {
        HeroAttribute expectedAttributes = new HeroAttribute(1, 1, 8);
        HeroAttribute actualAttributes = mage.getStartingLevelAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testStartingLevelAttributesShouldNotBe128() {
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
        int expectedLevel = 5;
        for (int i = 1; i < expectedLevel; i++) {
            mage.levelUp();
        }
        int actualLevel = mage.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test
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
    void testCreateWeaponNameShouldBeCommonWand() {
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
        HeroAttribute expectedAttribute = new HeroAttribute(1, 0, 0);
        HeroAttribute actualAttribute = armor.getArmorAttribute();

        assertEquals(expectedAttribute, actualAttribute);
    }

    // A Hero should be able to equip a Weapon, the appropriate exceptions should be thrown if invalid (level requirement and type)
    @Test
    void testEquipWeaponDoesNotThrowsExceptions() {

        assertDoesNotThrow(() -> mage.equip(weapon));

        assertEquals(weapon.getRequiredLevel(), mage.getLevel());
    }

    @Test
    void testEquipWeaponRequiredLevelThrowsExceptions() {
        weaponInvalid = new Weapon("Common Staff", 3, WeaponType.STAFF, 2);

        Exception exception = assertThrows(InvalidWeaponException.class, () -> mage.equip(weaponInvalid));

        String expectedMessage = "This hero is not high enough level to equip " + weaponInvalid.getWeaponType();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEquipWeaponTypeThrowsExceptions() {
        weaponInvalid = new Weapon("Common Sword", 1, WeaponType.SWORD, 2);

        Exception exception = assertThrows(InvalidWeaponException.class, () -> mage.equip(weaponInvalid));

        String expectedMessage = "This hero cannot equip weapons of type " + weaponInvalid.getWeaponType();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    // A Hero should be able to equip Armor, the appropriate exceptions should be thrown if invalid (level requirement and type)
    @Test
    void testEquipArmorDoesNotThrowsExceptions() {

        assertDoesNotThrow(() -> mage.equip(armor));

        assertEquals(armor.getRequiredLevel(), mage.getLevel());
    }

    @Test
    void testEquipArmorTypeThrowsExceptions() {
        armorInvalid = new Armor("Common Cloth Chest", 1, Slot.BODY, ArmorType.PLATE, new HeroAttribute(1, 0, 0));

        Exception exception = assertThrows(InvalidArmorException.class, () -> mage.equip(armorInvalid));

        String expectedMessage = "This hero cannot equip armor of type " + armorInvalid.getArmorType();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEquipArmorRequiredLevelThrowsExceptions() {
        armorInvalid = new Armor("Common Cloth Chest", 2, Slot.BODY, ArmorType.CLOTH, new HeroAttribute(1, 0, 0));

        Exception exception = assertThrows(InvalidArmorException.class, () -> mage.equip(armorInvalid));

        String expectedMessage = "This hero is not high enough level to equip this armor.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Total attributes should be calculated correctly
    // With no equipment
    @Test
    void testCalculateTotalAttributesNoEquipment() {
        HeroAttribute expectedAttributes = new HeroAttribute(1, 1, 8);
        HeroAttribute actualAttributes = mage.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    // With one piece of armor
    @Test
    void testCalculateTotalAttributesOnePieceOfArmor() throws InvalidArmorException {
        mage.equip(armor);
        HeroAttribute expectedAttributes = new HeroAttribute(2, 1, 8);
        HeroAttribute actualAttributes = mage.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    // With two pieces of armor
    @Test
    void testCalculateTotalAttributesTwoPiecesOfArmor() throws InvalidArmorException {
        Armor armor2 = new Armor("Common Cloth Legs", 1, Slot.LEGS, ArmorType.CLOTH, new HeroAttribute(1, 0, 1));

        mage.equip(armor);
        mage.equip(armor2);

        HeroAttribute expectedAttributes = new HeroAttribute(3, 1, 9);
        HeroAttribute actualAttributes = mage.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }


    // With a replaced piece of armor (equip armor, then equip new armor in the same slot)
    @Test
    void testCalculateTotalAttributesReplacedPieceOfArmor() throws InvalidArmorException {
        Armor armor2 = new Armor("Uncommon Cloth Chest", 1, Slot.BODY, ArmorType.CLOTH, new HeroAttribute(1, 0, 1));
        mage.equip(armor);
        mage.equip(armor2);

        HeroAttribute expected = new HeroAttribute(2, 1, 9);
        HeroAttribute actual = mage.calculateTotalAttributes();

        assertEquals(expected, actual);
    }

    // Hero damage should be calculated properly
    // No weapon equipped
    @Test
    void testCalculateDamageNoWeaponEquipped() {
        double expectedDamage = 1.08;
        double actualDamage = mage.calculateDamage();

        assertEquals(expectedDamage, actualDamage);
    }
    // Weapon equipped
    @Test
    void testCalculateDamageWeaponEquipped() throws InvalidWeaponException {
        mage.equip(weapon);

        double expectedDamage = 2.16;
        double actualDamage = mage.calculateDamage();

        assertEquals(expectedDamage, actualDamage);
    }
    // Replaced weapon equipped (equip a weapon then equip a new weapon)
    @Test
    void testCalculateDamageReplacedWeaponEquipped() throws InvalidWeaponException {
        Weapon weaponReplace = new Weapon("Uncommon Staff", 1, WeaponType.STAFF, 3);
        mage.equip(weapon);
        mage.equip(weaponReplace);

        double expectedDamage = 3.24;
        double actualDamage = mage.calculateDamage();

        assertEquals(expectedDamage, actualDamage);
    }
    // Weapon and armor equipped
    @Test
    void testCalculateDamageWeaponAndArmorEquipped() throws InvalidWeaponException, InvalidArmorException {
        mage.equip(weapon);
        mage.equip(armor);

        double expectedDamage = 2.16;
        double actualDamage = mage.calculateDamage();

        assertEquals(expectedDamage, actualDamage);
    }

    @Test
    void testDisplay() throws InvalidWeaponException, InvalidArmorException {
        mage.equip(weapon);
        mage.equip(armor);

        String expectedOutput = """
                Name: TinTin
                Class: Mage
                Level: 1
                Total strength: 2
                Total dexterity: 1
                Total intelligence: 8
                Damage: 2.16
                """;

        String actualOutput =  mage.display();

        assertEquals(expectedOutput,actualOutput);
    }
}