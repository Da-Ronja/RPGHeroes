package org.hero.rpg.heros;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MageTest {

    private Hero mage;

    @BeforeEach // Same
    public void setUp() {
        mage = new Mage("TinTin");
    }

    @Test // Same but maybe not done Should it test items Null?
    public void testInitializationNewMage() {
        assertEquals("TinTin", mage.getName());
        assertEquals(1, mage.getLevel());
        assertEquals(new HeroAttribute(1, 1, 8), mage.totalAttributes());
    }

    @Test
        // Same
    void createMageNameShouldBeTinTin() {
        String expectedName = "TinTin";
        String actualName = mage.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
        // Same
    void createMageNameShouldNotBeFluffy() {
        String expectedName = "Fluffy";
        String actualName = mage.getName();

        assertNotEquals(expectedName, actualName);
    }

    @Test // Same
    public void testStartingLevelShouldBeOne() {
        int expectedLevel = 1;
        int actualLevel = mage.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test // Same
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
    public void testLevelUp() {
        int expectedLevel = 2;
        HeroAttribute expectedAttributes = new HeroAttribute(2, 2, 13);

        mage.levelUp();

        int actualLevel = mage.getLevel();
        HeroAttribute actualAttributes = mage.totalAttributes();

        assertEquals(expectedLevel, actualLevel);
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test // Same
    public void testLevelIncreasedByOneWithLeveUp() {
        int expectedLevel = 2;
        mage.levelUp();
        int actualLevel = mage.getLevel();

        assertEquals(expectedLevel, actualLevel);
    }

    @Test // Same
    public void testAttributesIncreasedByCorrectAmount() {
        HeroAttribute expectedAttributes = new HeroAttribute(2, 2, 13);
        mage.levelUp();
        HeroAttribute actualAttributes = mage.calculateTotalAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }

}