package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {

    }

    Deck<AdventureCard> adventureDeck = new Deck<>();
    Deck<EventCard> eventDeck = new Deck<>();


    public void initializeAdventureDeck(){
        addFoeCards();
        addWeaponCards();
        adventureDeck.shuffleCards();
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

    public EventCard drawEventCard(){
        return eventDeck.drawCard();
    }

    public void initializeEventDeck(){
        addQCards();
        addECards();
        eventDeck.shuffleCards();
    }

    private void addQCards(){
        int[] qCardStages = {3,4,3,2};

        for(int i = 0; i<4 ; i++){
            for(int j =0; j<qCardStages[i]; j++){
                eventDeck.addCard(new EventCard(i+2));
            }
        }
    }

    private void addECards(){
        eventDeck.addCard(new EventCard(EventCard.Type.Plague));
        eventDeck.addCard(new EventCard(EventCard.Type.QueensFavor));
        eventDeck.addCard(new EventCard(EventCard.Type.QueensFavor));
        eventDeck.addCard(new EventCard(EventCard.Type.Prosperity));
        eventDeck.addCard(new EventCard(EventCard.Type.Prosperity));
    }

    public int getEventDeckSize(){
        return eventDeck.getSize();
    }

    public void initializePlayers(){

    }

    public int getPlayerHandSize(int pNo){
        return -1;
    }
}