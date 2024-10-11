package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {


    private final ArrayList<AdventureCard> hand;

    public Player(){
        hand = new ArrayList<>();
    }

    public void addToHand(AdventureCard card){
        hand.add(card);
    }

    public int getHandSize(){
        return hand.size();
    }

    public List<AdventureCard> getHand(){
        return hand;
    }
}
