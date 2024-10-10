package org.example;

public class AdventureCard {
    public enum Type{
        none, Foe, Dagger, Horse, Sword, BattleAxe, Lance, Excalibur
    }

    public Type getType(){
        return Type.none;
    }

    public int getValue() {
        return 0;
    }


}

