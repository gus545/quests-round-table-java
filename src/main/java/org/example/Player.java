package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player> {

    private static int nextId = 1;

    private final ArrayList<AdventureCard> hand;
    private int shields;
    private final int id;

    public Player(){
        hand = new ArrayList<>();
        shields = 0;
        id = nextId++;

        if (nextId>4) nextId = 1; //breaks if more than 4 players in game
    }

    public void addToHand(AdventureCard card){

        hand.add(card);
        sortHand();
    }

    private void sortHand(){
        hand.sort(new AdventureCardComparator());
    }

    public int getHandSize(){
        return hand.size();
    }

    public List<AdventureCard> getHand(){
        return hand;
    }

    public AdventureCard discardCard(int i){
        return hand.remove(i);
    }

    public void overrideShields(int n){
        this.shields = n;
    }

    public int getShields() { return this.shields;}

    public void deductShields(int n) {
        if (shields < n) shields = 0;
        else shields-=n;
    }

    @Override
    public int compareTo(Player other){
        return Integer.compare(this.id,other.id);
    }

    @Override
    public String toString(){
        return "P" + this.id;
    }
}
