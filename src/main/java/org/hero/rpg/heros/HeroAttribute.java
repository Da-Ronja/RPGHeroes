package org.hero.rpg.heros;

public class HeroAttribute {

    private final int strength;       // physical strength of the character.
    private final int dexterity;      // characters ability to attack with speed and nimbleness.
    private final int intelligence;   // determines the characters affinity with magic.

    public HeroAttribute(int strength, int dexterity, int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public HeroAttribute plus(HeroAttribute other) {
        int newStrength = this.strength + other.strength;
        int newDexterity = this.dexterity + other.dexterity;
        int newIntelligence = this.intelligence + other.intelligence;
        return new HeroAttribute(newStrength, newDexterity, newIntelligence);
    }

    @Override
    public String toString() {
        return "HeroAttribute{" + "strength=" + strength + ", dexterity=" + dexterity + ", intelligence=" + intelligence + '}';
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HeroAttribute other)) {
            return false;
        }
        return this.strength == other.strength && this.dexterity == other.dexterity && this.intelligence == other.intelligence;
    }
}
