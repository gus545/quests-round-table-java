package org.example;

import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static final int NUM_PLAYERS = 4;

    public static void main(String[] args) {
        Main game = new Main(new PrintWriter(System.out), new Scanner(System.in));
        game.dealCards(12);
        game.gameLoop();
    }

    public void gameLoop(){
        giveHotseat(0);
        while (true){;
            pullNextEventCard();
            handleCurrentEvent();
            if(!getWinners().isEmpty()){
                displayWinners(getWinners());
                break;
            }
            endCurrentHotseat();
            giveHotseat(getNextPlayer(new ArrayList<>()));
        }
    }

    Deck<AdventureCard> adventureDeck,adventureDiscard;
    Deck<EventCard> eventDeck,eventDiscard;
    List<Player> players;
    EventCard.Type currentEvent;
    PrintWriter pw;
    Scanner sc;
    Quest currentQuest;
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
                adventureDeck.addCard(new AdventureCard(val));
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
        int[] weaponCounts = {6,12,16,8,6,2};

        for(int i = 0; i<6; i++){
            for(int j=0; j<weaponCounts[i]; j++){
                adventureDeck.addCard(new AdventureCard(weaponTypes[i]));
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
        StringBuilder out = new StringBuilder();
        out.append("P").append(pNo + 1).append("'s hand:\n");
        for(int i = 0; i < hand.size(); i++){
            out.append("(").append((i + 1)).append(")").append(hand.get(i)).append("  ");
        }
        pw.println(out);
    }

    public void discardFromHand(int playerNo, int cardNo){
        adventureDiscard.addCard(players.get(playerNo).discardCard(cardNo));
    }

    public void trimPlayerHand(int pNo){
        while(players.get(pNo).getHandSize() > 12){
            displayPlayerHand(pNo);
            String input = promptInput("Enter the index of the card you would like to discard:");
            pw.println(getPlayer(pNo) + " discarded a " + getPlayer(pNo).getHand().get(Integer.parseInt(input)-1)+ " card");
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
        promptInput(prompt);
    }

    public int getCurrentPlayer(){
        return currentPlayer;
    }
    public void endCurrentHotseat(){
        String prompt = "Your turn has ended (for now).  Press <return> then let the next player take over:";
        promptInput(prompt);

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
            buildQuest();
            for(int i = 0; i<currentQuest.getNumStages(); i++){
                promptParticipants();
                List<Attack> attacks = promptAttacks();
                resolveStage(i,attacks);
                if(currentQuest.getParticipants().isEmpty()) break;
            }
            wrapUpQuest();
        }
    }

    public boolean findSponsor(){
        int start = currentPlayer;
        String playerInput;
        do{
            displayPlayerHand(currentPlayer);
            playerInput = promptInput("Would you (" +getPlayer(currentPlayer)+ ") like to sponsor this quest?(y/n)");
            playerInput = playerInput.strip().toLowerCase();
            if(playerInput.equals("y") || playerInput.equals("yes")) {
                pw.println(getPlayer(currentPlayer) + " has agreed to sponsor the quest.");
                return true;
            }
            pw.println(getPlayer(currentPlayer) + " has declined to sponsor the quest.");
            endCurrentHotseat();
            giveHotseat(getNextPlayer(new ArrayList<>()));
        } while (currentPlayer != start);

        pw.println("All players have declined to sponsor. Moving on...");
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
        Quest quest = new Quest(currentStages,players,getPlayer(currentPlayer));
        for(int i = 1; i<=currentStages; i++) quest.addStage(getSponsorChoices(i, quest.getMax()));
        currentQuest = quest;
        return quest;
    }

    public Attack buildAttack(){
        Player player = getPlayer(currentPlayer);
        Attack attack = new Attack(player);
        String playerInput;
        while (true){
            displayPlayerHand(currentPlayer);
            pw.println("Current attack: " + attack);
            pw.println("Current value: "+attack.getValue());
            playerInput = promptInput("Choose a card to add to your attack:");
            if (playerInput.equalsIgnoreCase("quit")) break;
            int index = Integer.parseInt(playerInput)-1;
            if(attack.checkCardValidity(player.getHand().get(index),pw)){
                pw.println(player.getHand().get(index) + " added to attack.");
                attack.addCard(player.discardCard(index));
            }
        }
        pw.println("Total attack: " + attack);
        pw.println("Total value: " + attack.getValue());
        return attack;
    }

    public Quest getCurrentQuest(){
        return currentQuest;
    }

    public PrintWriter getPw(){return pw;}

    public void promptParticipants(){
        List<Integer> ineligible = currentQuest.getIneligibleIds();
        for(int i=0; i<NUM_PLAYERS; i++) {
            if(!ineligible.contains(i)){
                giveHotseat(i);
                displayPlayerHand(currentPlayer);
                String playerInput = promptInput("Will you participate in this quest stage? (" + getPlayer(currentPlayer) + ")").strip();
                if (playerInput.equalsIgnoreCase("y")) {
                    AdventureCard newCard = drawAdventureCard();
                    getPlayer(currentPlayer).addToHand(newCard);
                    pw.println(getPlayer(currentPlayer) + " draws a " + newCard + " card.");
                    if (getPlayer(currentPlayer).getHandSize() > 12) trimPlayerHand(currentPlayer);
                } else {
                    pw.println("Removing " + getPlayer(currentPlayer) + " from current quest...");
                    currentQuest.removeParticipants(getPlayer(currentPlayer));
                }

                endCurrentHotseat();
            }
        }
    }
    public List<Attack> promptAttacks(){
        ArrayList<Attack> attacks = new ArrayList<>();
        List<Integer> ineligible = currentQuest.getIneligibleIds();
        for(int i = 0 ; i < NUM_PLAYERS ; i++) {
            if(!ineligible.contains(i)) {
                    endCurrentHotseat();
                    giveHotseat(i);
                    attacks.add(buildAttack());
            }
        }
        endCurrentHotseat();
        return attacks;
    }

    public void setQuest(Quest q){
        currentQuest = q;
    }

    public List<Player> getPlayers(){return players;}

    public void resolveStage(int stage, List<Attack> attacks){
        currentQuest.resolveAttack(stage,attacks,pw);
        for(Attack a: attacks){
            adventureDiscard.addCards(a.getCards());
        }
    }

    public void wrapUpQuest(){
        for (Player p : currentQuest.getParticipants()) {
            p.addShields(currentQuest.getNumStages());
        }
        giveHotseat(currentQuest.getSponsor().getId() - 1);
        for (int i = 0; i < currentQuest.getNumCards() + currentQuest.getNumStages(); i++) {
            getPlayer(currentPlayer).addToHand(drawAdventureCard());
        }
        if (getPlayer(currentPlayer).getHandSize() > 12) trimPlayerHand(currentPlayer);

        adventureDiscard.addCards(currentQuest.getCards());
    }


    public void rigPlayerHand(int i, List<AdventureCard> newCards){
        Player player = getPlayer(i);
        adventureDeck.addCards(player.discardHand());
        adventureDiscard.shuffleCards();
        for(AdventureCard c : newCards) player.addToHand(c);
    }

    public void rigAdventureDeck(List<AdventureCard> newCards){
        adventureDeck.rigDeck(newCards);
    }

    public void rigEventDeck(List<EventCard> newCards){
        eventDeck.rigDeck(newCards);
    }
}