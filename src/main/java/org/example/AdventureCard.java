package org.example;

public class AdventureCard {
    public enum Type{
        Foe, Dagger, Horse, Sword, BattleAxe, Lance, Excalibur
    }

    private final Type type;
    private final int value;

    public AdventureCard(AdventureCard.Type type, int value){
        this.type = type;
        this.value = value;
    }

    public Type getType(){
        return type;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AdventureCard otherCard = (AdventureCard) obj;

        return this.type == otherCard.type && this.value == otherCard.value;
    }

    @Override
    public String toString() {
        char typeInitial = Character.toUpperCase(type.name().charAt(0));

        return typeInitial + String.valueOf(value);
    }
}

