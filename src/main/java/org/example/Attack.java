package org.example;

import java.util.ArrayList;

public class Attack {

    private final ArrayList<AdventureCard> cards;
    private final Player player;

    

    public Attack(Player player){
        cards = new ArrayList<>();
        this.player = player;
    }

    public ArrayList<AdventureCard> getCards(){return cards;}

    public Player getPlayer(){return player;}

    public void addCard(AdventureCard card){cards.add(card);}

}
