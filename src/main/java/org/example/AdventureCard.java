package org.example;

import java.util.Objects;

public class AdventureCard {

    public enum Type{
        Foe, Dagger, Horse, Sword, BattleAxe, Lance, Excalibur
    }

    private final Type type;
    private final int value;

    public AdventureCard(AdventureCard.Type type){
        this.type = type;
        this.value = switch (type){
            case Type.Foe, Type.Dagger -> 5;
            case Type.Sword, Type.Horse -> 10;
            case Type.BattleAxe -> 15;
            case Type.Lance -> 20;
            case Type.Excalibur -> 30;

        };
    }

    public AdventureCard(int value){
        this.value = value;
        this.type = Type.Foe;
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

