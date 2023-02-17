package org.hero.rpg.heros;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {

    private Hero mage;

    @BeforeEach
    public void setUp() {
        mage = new Mage("TinTin");
    }

    //  When a Hero is created, it needs to have the correct name, level, and attributes.
    @Test
    void TestCreateMage_Name_ShouldPass() {
        assertEquals("TinTin", mage.getName());
    }

    @Test
    public void testStartingLevelAttributes() {
        assertEquals(new HeroAttribute(1, 1, 8), mage.totalAttributes());
    }

    @Test
    public void testCalculateTotalAttributes() {
        HeroAttribute expectedAttributes = new HeroAttribute(1, 1, 8);
        HeroAttribute totalAttributes = mage.calculateTotalAttributes();
        assertEquals(expectedAttributes, totalAttributes);
    }

    @Test
    public void testStartingLevelShouldBeOne() {
        assertEquals(1, mage.getLevel());
    }

    @Test
    public void testInitialization() {
        assertEquals("TinTin", mage.getName());
        assertEquals(1, mage.getLevel());
        assertEquals(new HeroAttribute(1, 1, 8), mage.totalAttributes());
    }


    // When a Heroes level is increased, it needs to increment by the correct amount and result in the correct
    //attributes.
    //o Creation and leveling tests need to be written for each sub class
    //o A test to see if HeroAttribute is being added/increased correctly should also be written
    @Test
    public void testLevelUp() {
        mage.levelUp();
        assertEquals(2, mage.getLevel());
        assertEquals(new HeroAttribute(2, 2, 13), mage.totalAttributes());
    }

}