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
    }
}
