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


}

