package org.hero.rpg.heros;

import org.hero.rpg.equipment.*;
import org.hero.rpg.exception.InvalidArmorException;
import org.hero.rpg.exception.InvalidWeaponException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangerTest {

    private Hero ranger;
    private Weapon weapon;
    private Weapon weaponInvalid;
    private Armor armor;
    private Armor armorInvalid;

    @BeforeEach
    public void setUp() {
        ranger = new Ranger("Ranger Tom");
        weapon = new Weapon("Common Bow", 1, WeaponType.BOW, 2);
        armor = new Armor("Common Leather Chest", 1, Slot.BODY, ArmorType.LEATHER, new HeroAttribute(1, 0, 0));
    }

    @Test
    void createMageNameShouldBeRangerTom() {
        String expectedName = "Ranger Tom";
        String actualName = ranger.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void createMageNameShouldNotBeFluffy() {
        String expectedName = "Fluffy";
        String actualName = ranger.getName();

        assertNotEquals(expectedName, actualName);
    }


    @Test
    public void testStartingLevelShouldBeOne() {
        int expectedLevel = 1;
        int actualLevel = ranger.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testStartingLevelShouldNotBeFive() {
        int expectedLevel = 5;
        int actualLevel = ranger.getLevel();

        assertNotEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testStartingLevelAttributesShouldBe171() {
        HeroAttribute expectedAttributes = new HeroAttribute(1, 7, 1);
        HeroAttribute actualAttributes = ranger.getStartingLevelAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testStartingLevelAttributesShouldNotBe123() {
        HeroAttribute expectedAttributes = new HeroAttribute(1, 2, 3);
        HeroAttribute actualAttributes = ranger.getStartingLevelAttributes();

        assertNotEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testLevelIncreasedByOneWithLeveUp() {
        int expectedLevel = 2;
        ranger.levelUp();
        int actualLevel = ranger.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testLevelIncreasedByFiveWithLeveUp() {
        int expectedLevel = 5;
        for (int i = 1; i < expectedLevel; i++) {
            ranger.levelUp();
        }
        int actualLevel = ranger.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testAttributesIncreasedByCorrectAmountWhenLeveUpByOne() {
        HeroAttribute expectedAttributes = new HeroAttribute(2, 12, 2);
        ranger.levelUp();
        HeroAttribute actualAttributes = ranger.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void testAttributesIncreasedByCorrectAmountWhenLeveUpByFive() {
        HeroAttribute expectedAttributes = new HeroAttribute(5, 27, 5);
        for (int i = 1; i < 5; i++) {
            ranger.levelUp();
        }
        HeroAttribute actualAttributes = ranger.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    // When Weapon is created, it needs to have the correct name, required level, slot, weapon type, and damage
    @Test
    void testCreateWeaponNameShouldBeCommonBow() {
        String expectedName = "Common Bow";
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
    void testCreateWeaponTypeShouldBeBow() {
        WeaponType expectedType = WeaponType.BOW;
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
    void testCreateArmorNameShouldBeCommonLeatherChest() {
        String expectedName = "Common Leather Chest";
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
    void testCreateArmorTypeShouldBeLeather() {
        ArmorType expectedType = ArmorType.LEATHER;
        ArmorType actualType = armor.getArmorType();

        assertEquals(expectedType, actualType);
    }

    @Test
    void testCreateArmorAttributeShouldBeLeather() {
        HeroAttribute expectedAttribute = new HeroAttribute(1, 0, 0);
        HeroAttribute actualAttribute = armor.getArmorAttribute();

        assertEquals(expectedAttribute, actualAttribute);
    }

    // A Hero should be able to equip a Weapon, the appropriate exceptions should be thrown if invalid (level requirement and type)
    @Test
    void testEquipWeaponDoesNotThrowsExceptions() {

        assertDoesNotThrow(() -> ranger.equip(weapon));

        assertEquals(weapon.getRequiredLevel(), ranger.getLevel());
    }

    @Test
    void testEquipWeaponRequiredLevelThrowsExceptions() {
        weaponInvalid = new Weapon("Common Bow", 3, WeaponType.BOW, 2);

        Exception exception = assertThrows(InvalidWeaponException.class, () -> ranger.equip(weaponInvalid));

        String expectedMessage = "This hero is not high enough level to equip " + weaponInvalid.getWeaponType();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEquipWeaponTypeThrowsExceptions() {
        weaponInvalid = new Weapon("Common Wand", 1, WeaponType.WAND, 2);

        Exception exception = assertThrows(InvalidWeaponException.class, () -> ranger.equip(weaponInvalid));

        String expectedMessage = "This hero cannot equip weapons of type " + weaponInvalid.getWeaponType();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // A Hero should be able to equip Armor, the appropriate exceptions should be thrown if invalid (level requirement and type)
    @Test
    void testEquipArmorDoesNotThrowsExceptions() {

        assertDoesNotThrow(() -> ranger.equip(armor));

        assertEquals(armor.getRequiredLevel(), ranger.getLevel());
    }

    @Test
    void testEquipArmorTypeThrowsExceptions() {
        armorInvalid = new Armor("Common Cloth Chest", 1, Slot.BODY, ArmorType.CLOTH, new HeroAttribute(1, 0, 0));

        Exception exception = assertThrows(InvalidArmorException.class, () -> ranger.equip(armorInvalid));

        String expectedMessage = "This hero cannot equip armor of type " + armorInvalid.getArmorType();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEquipArmorRequiredLevelThrowsExceptions() {
        armorInvalid = new Armor("Common Leather Chest", 2, Slot.BODY, ArmorType.LEATHER, new HeroAttribute(1, 0, 0));

        Exception exception = assertThrows(InvalidArmorException.class, () -> ranger.equip(armorInvalid));

        String expectedMessage = "This hero is not high enough level to equip this armor.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Total attributes should be calculated correctly
    // With no equipment
    @Test
    void testCalculateTotalAttributesNoEquipment() {
        HeroAttribute expectedAttributes = new HeroAttribute(1, 7, 1);
        HeroAttribute actualAttributes = ranger.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    // With one piece of armor
    @Test
    void testCalculateTotalAttributesOnePieceOfArmor() throws InvalidArmorException {
        ranger.equip(armor);
        HeroAttribute expectedAttributes = new HeroAttribute(2, 7, 1);
        HeroAttribute actualAttributes = ranger.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    // With two pieces of armor
    @Test
    void testCalculateTotalAttributesTwoPiecesOfArmor() throws InvalidArmorException {
        Armor armor2 = new Armor("Rar Leather Legs", 1, Slot.LEGS, ArmorType.LEATHER, new HeroAttribute(1, 0, 1));

        ranger.equip(armor);
        ranger.equip(armor2);

        HeroAttribute expectedAttributes = new HeroAttribute(3, 7, 2);
        HeroAttribute actualAttributes = ranger.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

    // With a replaced piece of armor (equip armor, then equip new armor in the same slot)
    @Test
    void testCalculateTotalAttributesReplacedPieceOfArmor() throws InvalidArmorException {
        Armor armor2 = new Armor("Uncommon Mail Chest", 1, Slot.BODY, ArmorType.MAIL, new HeroAttribute(1, 0, 1));
        ranger.equip(armor);
        ranger.equip(armor2);

        HeroAttribute expected = new HeroAttribute(2, 7, 2);
        HeroAttribute actual = ranger.calculateTotalAttributes();

        assertEquals(expected, actual);
    }

    // Hero damage should be calculated properly
    // No weapon equipped
    @Test
    void testCalculateDamageNoWeaponEquipped() {
        double expectedDamage = 1.07;
        double actualDamage = ranger.calculateDamage();

        assertEquals(expectedDamage, actualDamage);
    }

    // Weapon equipped
    @Test
    void testCalculateDamageWeaponEquipped() throws InvalidWeaponException {
        ranger.equip(weapon);

        double expectedDamage = 2.14;
        double actualDamage = ranger.calculateDamage();

        assertEquals(expectedDamage, actualDamage);
    }

    // Replaced weapon equipped (equip a weapon then equip a new weapon)
    @Test
    void testCalculateDamageReplacedWeaponEquipped() throws InvalidWeaponException {
        Weapon weaponReplace = new Weapon("Rar Bow", 1, WeaponType.BOW, 3);
        ranger.equip(weapon);
        ranger.equip(weaponReplace);

        double expectedDamage = 3.21;
        double actualDamage = ranger.calculateDamage();

        assertEquals(expectedDamage, actualDamage);
    }

    // Weapon and armor equipped
    @Test
    void testCalculateDamageWeaponAndArmorEquipped() throws InvalidWeaponException, InvalidArmorException {
        ranger.equip(weapon);
        ranger.equip(armor);

        double expectedDamage = 2.14;
        double actualDamage = ranger.calculateDamage();

        System.out.println(ranger.display());

        assertEquals(expectedDamage, actualDamage);
    }

    @Test
    void testDisplay() throws InvalidWeaponException, InvalidArmorException {
        ranger.equip(weapon);
        ranger.equip(armor);

        String expectedOutput = """
                Name: Ranger Tom
                Class: Ranger
                Level: 1
                Total strength: 2
                Total dexterity: 7
                Total intelligence: 1
                Damage: 2.14
                """;

        String actualOutput = ranger.display();

        assertEquals(expectedOutput, actualOutput);
    }

}