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

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", requiredLevel=" + requiredLevel +
                ", slot=" + slot +
                '}';
    }

    // TODO Remove
    public void setName(String name) {
        this.name = name;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Object getDamageMultiplier() {
        return null;
    }

    public String getName() {
        return name;
    }


}
