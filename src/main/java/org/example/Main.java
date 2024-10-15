package org.example;

import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static final int NUM_PLAYERS = 4;

    public static void main(String[] args) {

    }

    Deck<AdventureCard> adventureDeck,adventureDiscard;
    Deck<EventCard> eventDeck,eventDiscard;
    List<Player> players;
    EventCard.Type currentEvent;
    PrintWriter pw;
    Scanner sc;
    int currentPlayer;
    int currentStages;

    public Main(PrintWriter pw, Scanner sc){
        adventureDeck = new Deck<>();
        initializeAdventureDeck();

        eventDeck = new Deck<>();
        initializeEventDeck();

        adventureDiscard = new Deck<>();
        eventDiscard = new Deck<>();

        players = new ArrayList<>();
        initializePlayers();
        currentPlayer = 0;

        currentEvent = null;

        this.sc = sc;
        this.pw = pw;

    }
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

    public void displayPlayerHand(int pNo){
        List<AdventureCard> hand = players.get(pNo).getHand();

        pw.println("P"+(pNo+1)+"'s hand:");
        for(int i = 0; i < hand.size(); i++){
            pw.println((i + 1) + " - "+ hand.get(i));
        }
    }

    public void discardFromHand(int playerNo, int cardNo){
        adventureDiscard.addCard(players.get(playerNo).discardCard(cardNo));
    }

    public void trimPlayerHand(int pNo){
        while(players.get(pNo).getHandSize() > 12){
            displayPlayerHand(pNo);
            String input = promptInput("Enter the index of the card you would like to discard:");
            discardFromHand(pNo, Integer.parseInt(input)-1);
        }
    }

    public String promptInput(String str){
        pw.println(str);
        pw.flush();

        return sc.nextLine();
    }

    public void giveHotseat(int pNo){
        currentPlayer = pNo;
        String prompt = "It is now P" + (pNo + 1) + "'s turn.  Press <return> to proceed:";
        String str = promptInput(prompt);
    }

    public int getCurrentPlayer(){
        return currentPlayer;
    }
    public void endCurrentHotseat(){
        String prompt = "Your turn has ended (for now).  Press <return> then let the next player take over:";
        String str = promptInput(prompt);

        for(int i = 0; i<50 ; i++) {
            pw.println();
            pw.flush();
        }
    }

    public List<Player> getWinners(){
        List<Player> winners = new ArrayList<>();

        for(Player p : players) if (p.getShields()>=7) winners.add(p);

        return winners;
    }

    public void displayWinners(List<Player> winners){
        StringBuilder winnerString = new StringBuilder();
        for(Player w : winners) winnerString.append(w).append("  ");
        pw.println("Game has ended.  The winners are:");
        pw.println(winnerString);
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
        eventDeck.setCard(i,card);
    }

    public int getEventDiscardSize(){
        return eventDiscard.getSize();
    }

    public void pullNextEventCard() {
        EventCard newCard = drawEventCard();
        EventCard.Type type = newCard.getType();
        currentEvent = type;
        currentStages = newCard.getStages();
        eventDiscard.addCard(newCard);

        String base = "A "+ newCard + " card was drawn.  ";


        switch (type) {
            case EventCard.Type.Quest -> pw.println(base);
            case EventCard.Type.QueensFavor -> pw.println(base + getPlayer(getCurrentPlayer()) + " draws 2 cards.");
            case EventCard.Type.Prosperity -> pw.println(base + "All players draw 2 cards.");
            case EventCard.Type.Plague -> pw.println(base + getPlayer(getCurrentPlayer()) + " loses 2 shields.");
        }
    }


    public EventCard.Type getCurrentEvent() {
        return currentEvent;
    }

    public void handleCurrentEvent() {
        switch (currentEvent){
            case EventCard.Type.Plague -> getPlayer(getCurrentPlayer()).deductShields(2);
            case EventCard.Type.QueensFavor -> {
                for(int i = 0; i<2; i++) getPlayer(getCurrentPlayer()).addToHand(drawAdventureCard());
                if(getPlayer(getCurrentPlayer()).getHandSize()>12){
                    pw.println(getPlayer(getCurrentPlayer()) + " has more than 12 cards!");
                    trimPlayerHand(getCurrentPlayer());
                }
            }
            case EventCard.Type.Prosperity -> {
                dealCards(2);
                trimEachPlayerHand();
            }
            case EventCard.Type.Quest -> handleCurrentQuest();
        }
    }

    private void trimEachPlayerHand(){
        ArrayList<Integer> ignoreTrim = new ArrayList<>();
        for (int i = 0 ; i<NUM_PLAYERS ; i++) if (getPlayer(i).getHandSize()<=12) ignoreTrim.add(i);
        int start = currentPlayer;

        if (ignoreTrim.size()<NUM_PLAYERS){
            do {
                if (getPlayer(currentPlayer).getHandSize() > 12) {
                    pw.println(getPlayer(currentPlayer) + " has too many cards!");
                    trimPlayerHand(currentPlayer);
                }
                endCurrentHotseat();
                giveHotseat(getNextPlayer(ignoreTrim));
            } while (currentPlayer != start);
        }
    }

    private void handleCurrentQuest(){
        if(findSponsor()){
            pw.println(getPlayer(currentPlayer) + " has agreed to sponsor the quest.");
        }
        else pw.println("All players have declined to sponsor. Moving on...");
    }

    private boolean findSponsor(){
        int start = currentPlayer;
        String playerInput;
        do{
            displayPlayerHand(currentPlayer);
            playerInput = promptInput("Would you (" +getPlayer(currentPlayer)+ ") like to sponsor this quest?");
            playerInput = playerInput.strip().toLowerCase();
            if(playerInput.equals("y") || playerInput.equals("yes")) return true;
            endCurrentHotseat();
            giveHotseat(getNextPlayer(new ArrayList<>()));
        } while (currentPlayer != start);

        return false;
    }

    public Stage getSponsorChoices(int level, int max) {
        Stage stage = new Stage(level);
        Player sponsor = getPlayer(getCurrentPlayer());
        while(true){
            displayPlayerHand(currentPlayer);
            pw.println("Current stage cards:" + stage.getStageCardString());
            pw.println("Current stage value:" + stage.getStageValueString());
            String playerInput = promptInput("Choose a card to add to the stage (or type \"Quit\" to stop).");
            if(playerInput.equalsIgnoreCase("quit")) {
                if(!stage.containsFoe()) pw.println("Stage " + level + " cannot be completed.  A stage cannot be empty.");
                else if(stage.getValue()<= max) pw.println("Stage " + level + " cannot be completed.  Insufficient value for this stage.");
                else{
                    pw.println("Stage " + level + " completed" );
                    pw.println("Stage cards:" + stage.getStageCardString());
                    pw.println("Stage value:" + stage.getStageValueString());
                    return stage;
                }
            }
            else {
                int index = Integer.parseInt(playerInput)-1;
                if(stage.checkNewCardValidity(sponsor.getHand().get(index),pw)) {
                    AdventureCard newCard = sponsor.discardCard(index);
                    pw.println(newCard+ " added to stage.");
                    stage.addCard(newCard);
                }
            }

        }
    }

    public Quest buildQuest(){
        Quest quest = new Quest(currentStages);
        for(int i = 1; i<=currentStages; i++) quest.addStage(getSponsorChoices(i, quest.getMax()));
        return quest;
    }

    public Attack buildAttack(){
        Player player = getPlayer(currentPlayer);
        Attack attack = new Attack(player);
        String playerInput;
        while (true){
            displayPlayerHand(currentPlayer);
            playerInput = promptInput("Choose a card to add to your attack:");
            if (playerInput.equalsIgnoreCase("quit")) break;
            int index = Integer.parseInt(playerInput)-1;
            attack.addCard(player.discardCard(index));
        }
        return attack;
    }

}