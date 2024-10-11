package org.example;

import java.util.Comparator;

public class AdventureCardComparator implements Comparator<AdventureCard> {

    @Override
    public int compare(AdventureCard card1, AdventureCard card2) {
        if (card1.getType() != card2.getType()) {
            if (card1.getType() == AdventureCard.Type.Foe) {
                return -1;
            }
            if (card2.getType() == AdventureCard.Type.Foe) {
                return 1;
            }
        }

        int valueComparison = Integer.compare(card1.getValue(), card2.getValue());
        if (valueComparison != 0) {
            return valueComparison;
        }

        //for swords and horses
        if (card1.getType() == AdventureCard.Type.Sword && card2.getType() == AdventureCard.Type.Horse) {
            return -1;
        }
        if (card1.getType() == AdventureCard.Type.Horse && card2.getType() == AdventureCard.Type.Sword) {
            return 1;
        }

        return 0;
    }
}
