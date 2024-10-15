package org.example;

import java.io.PrintWriter;
import java.util.List;

public class Stage{

    public static boolean checkNewCardValidity(List<AdventureCard> currentCards, AdventureCard newCard, PrintWriter pw){
        if(newCard.getType()== AdventureCard.Type.Foe){
            boolean containsFoe = false;
            for (AdventureCard c : currentCards) if (c.getType()==AdventureCard.Type.Foe) {
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
        }else if(currentCards.contains(newCard)){
            pw.println(newCard+" is invalid.  No duplicates allowed.");
            return false;
        }else return true;

    }

    public static String getStageCardString(List<AdventureCard> cards){
        cards.sort(new AdventureCardComparator());
        StringBuilder out = new StringBuilder("Current stage cards:");

        for (AdventureCard c : cards){
            out.append(" ").append(c);
        }
        return out.toString();
    }
    public static String getStageValueString(List<AdventureCard> cards){
        int sum = 0;
        for(AdventureCard c : cards) sum+=c.getValue();
        return "Current stage value: " + sum;
    }

}
