package org.example;

import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static final int NUM_PLAYERS = 4;

    public static void main(String[] args) {

    }

    Deck<AdventureCard> adventureDeck = new Deck<>();
    Deck<EventCard> eventDeck = new Deck<>();
    Deck<AdventureCard> adventureDiscard = new Deck<>();
    List<Player> players = new ArrayList<>();
    int currentPlayer;

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

    public int getAdventureDiscardSize(){
        return adventureDiscard.getSize();
    }

    public void initializePlayers(){
        for(int i = 0; i<NUM_PLAYERS; i++) players.add(new Player());
    }

    public void dealCards(int nCards){
        for(Player p : players){
            for(int i =0; i<nCards; i++) p.addToHand(drawAdventureCard());
        }
    }

    public int getPlayerHandSize(int pNo){
        return players.get(pNo).getHandSize();
    }

    public Player getPlayer(int pNo){
        return players.get(pNo);
    }

    public void displayPlayerHand(int pNo, PrintWriter p){
        List<AdventureCard> hand = players.get(pNo).getHand();

        p.println("P"+(pNo+1)+"'s hand:");
        for(int i = 0; i < hand.size(); i++){
            p.println((i + 1) + " - "+ hand.get(i));
        }
    }

    public void discardFromHand(int playerNo, int cardNo){
        adventureDiscard.addCard(players.get(playerNo).discardCard(cardNo));
    }

    public void trimPlayerHand(int pNo, Scanner s, PrintWriter p){
        while(players.get(pNo).getHandSize() > 12){
            displayPlayerHand(pNo,p);
            String input = promptInput(s,p,"Enter the index of the card you would like to discard:");
            discardFromHand(pNo, Integer.parseInt(input)-1);
        }
    }

    public String promptInput(Scanner s, PrintWriter p, String str){
        p.println(str);
        p.flush();

        return s.nextLine();
    }

    public void giveHotseat(int pNo, Scanner s, PrintWriter p){
        currentPlayer = pNo;
        String prompt = "It is now P" + (pNo + 1) + "'s turn.  Press <return> to proceed:";
        String str = promptInput(s,p,prompt);
    }

    public int getCurrentPlayer(){
        return currentPlayer;
    }
    public void endCurrentHotseat(Scanner s, PrintWriter p){
        String prompt = "Your turn has ended (for now).  Press <return> then let the next player take over:";
        String str = promptInput(s,p, prompt);

        for(int i = 0; i<50 ; i++) {
            p.println();
            p.flush();
        }
    }

    public List<Player> getWinners(){
        List<Player> winners = new ArrayList<>();

        for(Player p : players) if (p.getShields()>=7) winners.add(p);

        return winners;
    }

    public void displayWinners(List<Player> winners, PrintWriter p){
        StringBuilder winnerString = new StringBuilder();
        for(Player w : winners) winnerString.append(w).append("  ");
        p.println("Game has ended.  The winners are:");
        p.println(winnerString);
    }

    public int getNextPlayer(List<Integer> ineligible){
        int i = getCurrentPlayer()+1;
        while(i!=getCurrentPlayer()){
            if(i>=NUM_PLAYERS) i=0;
            if(!ineligible.contains(i)) return i;
            i++;
        }
        return i;
    }


    public void overwriteEventDeck(int i, EventCard card) {
    }

    public int getEventDiscardSize(){
        return -1;
    }

    public void pullNextEventCard(PrintWriter pw) {
    }


    public EventCard.Type getCurrentEvent() {
        return null;
    }
}