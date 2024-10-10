package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {

    }

    Deck<AdventureCard> adventureDeck = new Deck<>();



    public void initializeAdventureDeck(){
        addFoeCards();
        addWeaponCards();
    }

    private void addFoeCards(){
        //Map of form (foe value, foe count)
        Map<Integer,Integer> foeCountValues = new HashMap<>();

        foeCountValues.put(5,8);
        foeCountValues.put(10,7);
        foeCountValues.put(15,8);
        foeCountValues.put(20,7);
        foeCountValues.put(25,7);
        foeCountValues.put(30,4);
        foeCountValues.put(35,4);
        foeCountValues.put(40,2);
        foeCountValues.put(50,2);
        foeCountValues.put(70,1);

        for (Map.Entry<Integer, Integer> pair : foeCountValues.entrySet()) {
            int val = pair.getKey();
            int count = pair.getValue();

            for(int i = 0; i<count; i++) {
                adventureDeck.addCard(new AdventureCard(AdventureCard.Type.Foe, val));
            }
        }


    }

    private void addWeaponCards(){
        AdventureCard.Type[] weaponTypes = {AdventureCard.Type.Dagger,
                                            AdventureCard.Type.Horse,
                                            AdventureCard.Type.Sword,
                                            AdventureCard.Type.BattleAxe,
                                            AdventureCard.Type.Lance,
                                            AdventureCard.Type.Excalibur
                                            };
        int[] weaponValues = {5,10,10,15,20,30};
        int[] weaponCounts = {6,12,16,8,6,2};

        for(int i = 0; i<6; i++){
            for(int j=0; j<weaponCounts[i]; j++){
                adventureDeck.addCard(new AdventureCard(weaponTypes[i],weaponValues[i]));
            }
        }
    }

    public int getAdventureDeckSize(){
        return adventureDeck.getSize();
    }

    public AdventureCard drawAdventureCard(){
        return adventureDeck.drawCard();
    }
}