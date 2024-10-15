package org.example;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Stage{

    private ArrayList<AdventureCard> cards;
    private int level;

    public Stage(int level){
        this.level = level;
        cards = new ArrayList<>();
    }

    public void addCard(AdventureCard c){
        cards.add(c);
        cards.sort(new AdventureCardComparator());
    }

    public boolean checkNewCardValidity(AdventureCard newCard, PrintWriter pw){
        if(newCard.getType()== AdventureCard.Type.Foe){
            boolean containsFoe = false;
            for (AdventureCard c : cards) if (c.getType()==AdventureCard.Type.Foe) {
                containsFoe = true;
                break;
            }
            if (containsFoe) {
                pw.println(newCard+ " is invalid.  Only one foe card allowed.");
                return false;
            }
            else{
                return true;
            }
        }else if(cards.contains(newCard)){
            pw.println(newCard+" is invalid.  No duplicates allowed.");
            return false;
        }else return true;

    }

    public String getStageCardString(){
       StringBuilder out = new StringBuilder();
       for (AdventureCard c : cards) out.append(" ").append(c);
       return out.toString();
    }
    public String getStageValueString(){
        int sum = 0;
        for(AdventureCard c : cards) sum+=c.getValue();
        return " " + sum;
    }

    public ArrayList<AdventureCard> getCards(){return cards;}

    public int getValue(){
        return 0;
    }
}
