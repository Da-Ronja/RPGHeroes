package org.hero.rpg.equipment;

public abstract class Item {
    String name;
    int requiredLevel;
    Slot slot;

    public Item(String name, int requiredLevel, Slot slot) {
        this.name = name;
        this.requiredLevel = requiredLevel;
        this.slot = slot;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public Slot getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", requiredLevel=" + requiredLevel +
                ", slot=" + slot +
                '}';
    }
}
