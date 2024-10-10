package org.example;

public class EventCard {
    public enum Type {
        Quest, Plague, QueensFavor, Prosperity
    }

    private final Type type;
    private final int stages; // Used only for quest cards, other cards will have stages = 0

    public EventCard(int stages) {
        this.type = Type.Quest;
        this.stages = stages;
    }

    public EventCard(Type type){
        this.type = type;
        this.stages = 0;
    }

    public Type getType() {
        return type;
    }

    public int getStages() {
        return stages;
    }
}
