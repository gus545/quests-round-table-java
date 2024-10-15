package org.example;

import java.io.PrintWriter;
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

    public void addCard(AdventureCard card){
        cards.add(card);
        cards.sort(new AdventureCardComparator());
    }

    public boolean checkCardValidity(AdventureCard card, PrintWriter pw){
        if(card.getType() == AdventureCard.Type.Foe){
            pw.println("Invalid choice. You cannot add foe cards to an attack.");
            return false;
        }else if(cards.contains(card)){
            pw.println("Invalid choice. You cannot add duplicate cards to an attack.");
            return false;
        }else return true;
    }

    public int getValue(){
        int sum = 0;
        for(AdventureCard c : cards) sum+= c.getValue();
        return sum;
    }

    @Override
    public String toString(){
        StringBuilder out = new StringBuilder();
        for(AdventureCard c : cards) out.append(c).append(" ");
        return out.toString();
    }



}
