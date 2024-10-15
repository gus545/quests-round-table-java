import org.example.*;
import org.junit.jupiter.api.*;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private Main game;
    private StringWriter output;
    private PrintWriter input;

    private int adventureDeckSize;
    private int eventDeckSize;

    @BeforeEach
    public void general_setup() throws Exception{
        PipedOutputStream outputStream = new PipedOutputStream();
        PipedInputStream inputStream = new PipedInputStream(outputStream);
        input = new PrintWriter(outputStream, true);
        output = new StringWriter();
        game = new Main(new PrintWriter(output), new Scanner(inputStream));
    }

    @Nested
    class RESP01Tests{
        @BeforeEach
        public void test_setup () {
            adventureDeckSize = game.getAdventureDeckSize();
        }

        @Test
        @DisplayName("Check if adventure deck size is 100")
        void RESP_01_test_01 () {
            assertEquals(100, adventureDeckSize, "There should be 100 adventure cards.");
        }

        @Test
        @DisplayName("Check if there are 50 foe cards and 50 weapon cards")
        void RESP_01_test_02 () {
            int foeCount = 0;
            int weaponCount = 0;


            List<AdventureCard.Type> weaponTypes = new ArrayList<>();
            weaponTypes.add(AdventureCard.Type.Dagger);
            weaponTypes.add(AdventureCard.Type.Horse);
            weaponTypes.add(AdventureCard.Type.Sword);
            weaponTypes.add(AdventureCard.Type.BattleAxe);
            weaponTypes.add(AdventureCard.Type.Lance);
            weaponTypes.add(AdventureCard.Type.Excalibur);


            for (int i = 0; i < adventureDeckSize; i++) {
                AdventureCard newCard = game.drawAdventureCard();
                if (newCard.getType() == AdventureCard.Type.Foe) {
                    foeCount++;
                }
                if (weaponTypes.contains(newCard.getType())) {
                    weaponCount++;
                }
            }

            assertEquals(50, foeCount, "There should be 50 foe cards in the deck");
            assertEquals(50, weaponCount, "There should be 50 weapon cards in the deck");
        }

        @Test
        @DisplayName("Check if there's the correct number of each type of foe card")
        void RESP_01_test_03 () {
            int f5count = 0;
            int f10count = 0;
            int f15count = 0;
            int f20count = 0;
            int f25count = 0;
            int f30count = 0;
            int f35count = 0;
            int f40count = 0;
            int f50count = 0;
            int f70count = 0;

            for (int i = 0; i < adventureDeckSize; i++) {
                AdventureCard newCard = game.drawAdventureCard();
                if (newCard.getType() == AdventureCard.Type.Foe) {
                    switch (newCard.getValue()) {
                        case 5:
                            f5count++;
                            break;
                        case 10:
                            f10count++;
                            break;
                        case 15:
                            f15count++;
                            break;
                        case 20:
                            f20count++;
                            break;
                        case 25:
                            f25count++;
                            break;
                        case 30:
                            f30count++;
                            break;
                        case 35:
                            f35count++;
                            break;
                        case 40:
                            f40count++;
                            break;
                        case 50:
                            f50count++;
                            break;
                        case 70:
                            f70count++;
                            break;

                    }
                }
            }

            assertEquals(8, f5count, "There should be 8 F5 cards in the deck.");
            assertEquals(7, f10count, "There should be 7 F10 cards in the deck.");
            assertEquals(8, f15count, "There should be 8 F15 cards in the deck.");
            assertEquals(7, f20count, "There should be 7 F20 cards in the deck.");
            assertEquals(7, f25count, "There should be 7 F25 cards in the deck.");
            assertEquals(4, f30count, "There should be 4 F30 cards in the deck.");
            assertEquals(4, f35count, "There should be 4 F35 cards in the deck.");
            assertEquals(2, f40count, "There should be 2 F40 cards in the deck.");
            assertEquals(2, f50count, "There should be 2 F50 cards in the deck.");
            assertEquals(1, f70count, "There should be 1 F70 card in the deck.");
        }

        @Test
        @DisplayName("Check for correct number of each type of weapon card")
        void RESP_01_test_04 () {
            int daggerCount = 0;
            int horseCount = 0;
            int swordCount = 0;
            int battleAxeCount = 0;
            int lanceCount = 0;
            int excaliburCount = 0;

            for (int i = 0; i < adventureDeckSize; i++) {
                AdventureCard newCard = game.drawAdventureCard();
                if (newCard.getType() == AdventureCard.Type.Dagger && newCard.getValue() == 5) {
                    daggerCount++;
                }
                if (newCard.getType() == AdventureCard.Type.Horse && newCard.getValue() == 10) {
                    horseCount++;
                }
                if (newCard.getType() == AdventureCard.Type.Sword && newCard.getValue() == 10) {
                    swordCount++;
                }
                if (newCard.getType() == AdventureCard.Type.BattleAxe && newCard.getValue() == 15) {
                    battleAxeCount++;
                }
                if (newCard.getType() == AdventureCard.Type.Lance && newCard.getValue() == 20) {
                    lanceCount++;
                }
                if (newCard.getType() == AdventureCard.Type.Excalibur && newCard.getValue() == 30) {
                    excaliburCount++;
                }

            }

            assertEquals(6, daggerCount, "There should be 6 D5 daggers in the deck.");
            assertEquals(12, horseCount, "There should be 12 H10 horses in the deck.");
            assertEquals(16, swordCount, "There should be 16 S10 swords in the deck.");
            assertEquals(8, battleAxeCount, "There should be 8 B15 battle-axes in the deck.");
            assertEquals(6, lanceCount, "There should be 6 L20 lances in the deck.");
            assertEquals(2, excaliburCount, "There should be 2 E30 excaliburs in the deck.");
        }
    }

    @Nested
    class RESP02Tests{
        @BeforeEach
        public void test_setup () {
            eventDeckSize = game.getEventDeckSize();
        }

        @Test
        @DisplayName("Check if event deck has 17 cards total")
        public void RESP_02_test_01 () {
            assertEquals(17, eventDeckSize);
        }

        @Test
        @DisplayName("Check if event deck has 5 E cards and 12 Q cards")
        public void RESP_02_test_02 () {
            int eCount = 0;
            int qCount = 0;

            for (int i = 0; i < eventDeckSize; i++) {
                EventCard newCard = game.drawEventCard();
                if (newCard.getType() == EventCard.Type.Quest) {
                    qCount++;
                }
                if (newCard.getType() == EventCard.Type.Plague ||
                        newCard.getType() == EventCard.Type.QueensFavor ||
                        newCard.getType() == EventCard.Type.Prosperity) {
                    eCount++;
                }
            }

            assertEquals(12, qCount, "There should be 12 Q cards in the event deck.");
            assertEquals(5, eCount, "There should be 5 E cards in the event deck.");
        }

        @Test
        @DisplayName("Check for correct number of Q cards of each stage value.")
        public void RESP_02_test_03 () {
            int q2Count = 0;
            int q3Count = 0;
            int q4Count = 0;
            int q5Count = 0;

            for (int i = 0; i < eventDeckSize; i++) {
                EventCard newCard = game.drawEventCard();

                if (newCard.getType() == EventCard.Type.Quest) {
                    switch (newCard.getStages()) {
                        case 2:
                            q2Count++;
                            break;
                        case 3:
                            q3Count++;
                            break;
                        case 4:
                            q4Count++;
                            break;
                        case 5:
                            q5Count++;
                            break;
                    }
                }
            }

            assertEquals(3, q2Count, "There should be 3 Q2 quest cards in the deck.");
            assertEquals(4, q3Count, "There should be 4 Q3 quest cards in the deck.");
            assertEquals(3, q4Count, "There should be 3 Q4 quest cards in the deck.");
            assertEquals(2, q5Count, "There should be 2 Q5 quest cards in the deck.");

        }

        @Test
        @DisplayName("Check for correct number of each type of E card.")
        public void RESP_02_test_04 () {
            int plagueCount = 0;
            int queensFavorCount = 0;
            int prosperityCount = 0;

            // Loop through the deck size and count each event card by type
            for (int i = 0; i < eventDeckSize; i++) {
                EventCard newCard = game.drawEventCard();

                switch (newCard.getType()) {
                    case EventCard.Type.Plague:
                        plagueCount++;
                        break;
                    case EventCard.Type.QueensFavor:
                        queensFavorCount++;
                        break;
                    case EventCard.Type.Prosperity:
                        prosperityCount++;
                        break;
                    default:
                        break;
                }
            }

            assertEquals(1, plagueCount, "There should be 1 Plague card in the deck.");
            assertEquals(2, queensFavorCount, "There should be 2 Queen's Favor cards in the deck.");
            assertEquals(2, prosperityCount, "There should be 2 Prosperity cards in the deck.");
        }
    }

    @Nested
    class RESP03Tests{
        @Test
        @DisplayName("Check if shuffling changes order of decks")
        public void RESP_03_test_01 () {

            adventureDeckSize = game.getAdventureDeckSize();
            eventDeckSize = game.getEventDeckSize();

            Main game2 = new Main(new PrintWriter(System.out), new Scanner(System.in));
            game2.initializeAdventureDeck();
            game2.initializeEventDeck();

            boolean adventureDecksAreDifferent = false;
            boolean eventDecksAreDifferent = false;

            for (int i = 0; i < adventureDeckSize; i++) {
                AdventureCard newCard1 = game.drawAdventureCard();
                AdventureCard newCard2 = game2.drawAdventureCard();

                if (newCard1.getType() != newCard2.getType() || newCard1.getValue() != newCard2.getValue())
                    adventureDecksAreDifferent = true;
            }

            for (int i = 0; i < eventDeckSize; i++) {
                EventCard newCard1 = game.drawEventCard();
                EventCard newCard2 = game2.drawEventCard();

                if (newCard1.getType() != newCard2.getType() || newCard1.getStages() != newCard2.getStages())
                    eventDecksAreDifferent = true;
            }

            assertTrue(adventureDecksAreDifferent, "Adventure decks should be different in at least one position.");
            assertTrue(eventDecksAreDifferent, "Event decks should be different in at least one position.");
        }
    }

    @Nested
    class RESP04Tests{
        @BeforeEach
        public void test_setup () {
            adventureDeckSize = game.getAdventureDeckSize();
            game.dealCards(12);
        }
        @Test
        @DisplayName("Check if each player has hand size 12")
        public void RESP_04_test_01 () {
            int numPlayers = 4;

            for (int i = 0; i < numPlayers; i++) {
                assertEquals(12, game.getPlayerHandSize(i),
                        "P" + (i + 1) + " should have a hand size of 12.");
            }
        }

        @Test
        @DisplayName("Check if deck size is correct after dealing 12 cards to each player")
        public void RESP_04_test_02 () {
            assertEquals(adventureDeckSize - 4 * 12, game.getAdventureDeckSize());
        }
    }

    @Nested
    class RESP05Tests{
        Player player;

        @BeforeEach
        public void test_setup () {
            player = game.getPlayer(0);

            //add out of order cards
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe, 10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe, 5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Horse, 10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword, 10));
            player.addToHand(new AdventureCard(AdventureCard.Type.BattleAxe,15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Excalibur,30));
        }

        @Test
        @DisplayName("Check player hand is in sorted order")
        public void RESP_05_test_01(){


            List<AdventureCard> hand = player.getHand();

            List<AdventureCard> expectedSortedHand = new ArrayList<>();
            expectedSortedHand.add(new AdventureCard(AdventureCard.Type.Foe, 5));
            expectedSortedHand.add(new AdventureCard(AdventureCard.Type.Foe, 10));
            expectedSortedHand.add(new AdventureCard(AdventureCard.Type.Sword, 10));
            expectedSortedHand.add(new AdventureCard(AdventureCard.Type.Horse, 10));
            expectedSortedHand.add(new AdventureCard(AdventureCard.Type.BattleAxe, 15));
            expectedSortedHand.add(new AdventureCard(AdventureCard.Type.Excalibur, 30));

            assertEquals(expectedSortedHand, hand, "Player's hand should be sorted in the expected order.");
        }



        @Test
        @DisplayName("Check all cards in hand are displayed correctly and in order.")
        public void RESP_05_test_02(){

            game.displayPlayerHand(0);

            assertTrue(output.toString().contains("1 - F5"));
            assertTrue(output.toString().contains("2 - F10"));
            assertTrue(output.toString().contains("3 - S10"));
            assertTrue(output.toString().contains("4 - H10"));
            assertTrue(output.toString().contains("5 - B15"));
            assertTrue(output.toString().contains("6 - E30"));


        }
    }

    @Nested
    class RESP06Tests{

        //all tests assume valid index

        int initialSize;
        AdventureCard discardedCard;

        @BeforeEach
        public void test_setup(){
            discardedCard = new AdventureCard(AdventureCard.Type.Foe,5);
            game.getPlayer(0).addToHand(discardedCard);
            game.getPlayer(0).addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            game.getPlayer(0).addToHand(new AdventureCard(AdventureCard.Type.BattleAxe,15));

            initialSize = game.getAdventureDiscardSize();
            game.discardFromHand(0,0);
        }

        @Test
        @DisplayName("Check if discard pile has grown")
        public void RESP_06_test_01(){
            assertEquals(initialSize + 1,game.getAdventureDiscardSize() );
        }

        @Test
        @DisplayName("Check if player's hand matches expectations")
        public void RESP_06_test_02(){
            List<AdventureCard> expectedHand = new ArrayList<>();
            expectedHand.add(new AdventureCard(AdventureCard.Type.Sword,10));
            expectedHand.add(new AdventureCard(AdventureCard.Type.BattleAxe,15));

            assertEquals(expectedHand, game.getPlayer(0).getHand());
        }

    }

    @Nested
    class RESP07Tests{

        Player player;

        @BeforeEach
        public void test_setup(){
            player = game.getPlayer(0);

            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,20));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,40));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Dagger,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Dagger,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Horse,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Excalibur,30));
            player.addToHand(new AdventureCard(AdventureCard.Type.Lance,20));

            input.println("1\n10\n6\n"); //only first 2 inputs should go through
            game.trimPlayerHand(0);
        }


        @Test
        @DisplayName("Test if prompt is correct")
        public void RESP_07_test_01(){
            assertTrue(output.toString().contains("Enter the index of the card you would like to discard:"), "Should properly prompt user.");
        }

        @Test
        @DisplayName("Test if discarded cards match input are discarded")
        public void RESP_07_test_02(){

            List<AdventureCard> expectedHand = new ArrayList<>();
            expectedHand.add(new AdventureCard(AdventureCard.Type.Foe,5));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Foe,10));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Foe,15));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Foe,15));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Foe,20));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Foe,40));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Dagger,5));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Dagger,5));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Sword,10));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Horse,10));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Lance,20));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Excalibur,30));

            assertEquals(expectedHand, player.getHand(), "Player should have discarded one F5 and one S10");
        }


        @Test
        @DisplayName("Test if player ends with 12 cards in hand")
        public void RESP_07_test_03(){
            assertEquals(12, player.getHandSize(), "Player should have 12 cards");
        }


        @Test
        @DisplayName("Check discard pile size")
        public void RESP_07_test_04(){
            assertEquals(2, game.getAdventureDiscardSize(), "Adventure discard pile should be 2");
        }
    }

    @Nested
    class RESP08Tests{
        @BeforeEach
        public void test_setup(){
            input.println("\n");
            game.giveHotseat(0);
        }

        @Test
        @DisplayName("Game correctly displays prompt to player")
        public void RESP_08_test_01(){
            assertTrue(output.toString().contains("Press <return> to proceed:"), "Game should display correct prompt.");
        }

        @Test
        @DisplayName("Game displays correct player's turn")
        public void RESP_08_test_02(){
            assertTrue(output.toString().contains("P1"), "Game should display that it is P1's turn");
            game.giveHotseat(1);
            assertTrue(output.toString().contains("P2"), "Game should display that it is P1's turn");
        }

        @Test
        @DisplayName("Current player changes to correct value")
        public void RESP_08_test_03(){
            assertEquals(0,game.getCurrentPlayer(), "Current player should reflect hotseat assignment");
            game.giveHotseat(3);
            assertEquals(3, game.getCurrentPlayer(), "Current player should reflect hotseat assignment");
        }
    }

    @Nested
    class RESP09Tests{
        @BeforeEach
        public void test_setup(){
            input.println("\n");
            game.giveHotseat(0);
            game.endCurrentHotseat();
        }

        @Test
        @DisplayName("Check user prompt is correct")
        public void RESP_09_test_01(){
            assertTrue(output.toString().contains("Your turn has ended (for now).  Press <return> then let the next player take over:"), "Game should prompt user correctly.");
        }

        @Test
        @DisplayName("Check screen is cleared.")
        public void RESP_09_test_02(){
            assertTrue(output.toString().split("\n").length>=50, "Output should contain at least 50 newlines");
        }
    }

    @Nested
    class RESP10Tests{

        List<Player> expectedWinners;

        @BeforeEach
        public void test_setup(){
            expectedWinners = new ArrayList<>();
        }

        @Test
        @DisplayName("Identify a single winner.")
        public void RESP_10_test_01(){
            game.getPlayer(0).overrideShields(0);
            game.getPlayer(1).overrideShields(4);
            game.getPlayer(2).overrideShields(9);
            game.getPlayer(3).overrideShields(5);

            expectedWinners.add(game.getPlayer(2));

            assertEquals(expectedWinners, game.getWinners(), "The only winner should be P3");

        }

        @Test
        @DisplayName("Identify multiple winners.")
        public void RESP_10_test_02(){
            game.getPlayer(0).overrideShields(8);
            game.getPlayer(1).overrideShields(4);
            game.getPlayer(2).overrideShields(9);
            game.getPlayer(3).overrideShields(5);

            expectedWinners.add(game.getPlayer(0));
            expectedWinners.add(game.getPlayer(2));

            assertEquals(expectedWinners, game.getWinners(), "The winners should be P1 and P3");


        }

        @Test
        @DisplayName("Identify when there are no winners.")
        public void RESP_10_test_03(){
            game.getPlayer(0).overrideShields(0);
            game.getPlayer(1).overrideShields(4);
            game.getPlayer(2).overrideShields(2);
            game.getPlayer(3).overrideShields(5);

            assertEquals(expectedWinners, game.getWinners(), "There should be no winners");
        }
    }

    @Nested
    class RESP11Tests{

        @Test
        @DisplayName("Check player is properly converted to string")
        public void RESP_11_test_01(){
            assertEquals("P1", game.getPlayer(0).toString(), "String should be P1");
            assertEquals("P2", game.getPlayer(1).toString(), "String should be P2");
            assertEquals("P3", game.getPlayer(2).toString(), "String should be P3");
            assertEquals("P4", game.getPlayer(3).toString(), "String should be P4");
        }

        @Test
        @DisplayName("Check output text")
        public void RESP_11_test_02(){
            game.displayWinners(new ArrayList<>());

            assertTrue(output.toString().contains("Game has ended.  The winners are:"), "Generic text display should be correct");
        }

        @Test
        @DisplayName("Display one winner")
        public void RESP_11_test_03(){
            List<Player> winners = new ArrayList<>();
            winners.add(game.getPlayer(0));

            game.displayWinners(winners);

            assertTrue(output.toString().contains("P1"), "Display should contain P1");
        }

        @Test
        @DisplayName("Display multiple winners")
        public void RESP_11_test_04(){

            List<Player> winners = new ArrayList<>();
            winners.add(game.getPlayer(0));
            winners.add(game.getPlayer(2));

            game.displayWinners(winners);

            assertTrue(output.toString().contains("P1") && output.toString().contains("P3"), "Display contains winners P1 and P2");
        }
    }

    @Nested
    class RESP12Tests{

        List<Integer> ineligible;
        @BeforeEach
        public void test_setup(){
            input.println("\n\n\n\n\n");
            game.giveHotseat(0);
            ineligible = new ArrayList<>();
        }

        @Test
        @DisplayName("Game advances to next player's turn with all players eligible")
        public void RESP_12_test_01(){
            game.giveHotseat(game.getNextPlayer(ineligible));
            assertEquals(1,game.getCurrentPlayer());
            game.giveHotseat(game.getNextPlayer(ineligible));
            assertEquals(2,game.getCurrentPlayer());
            game.giveHotseat(game.getNextPlayer(ineligible));
            assertEquals(3,game.getCurrentPlayer());
            game.giveHotseat(game.getNextPlayer(ineligible));
            assertEquals(0,game.getCurrentPlayer());
        }

        @Test
        @DisplayName("Game advances to next player's turn with multiple players ineligible")
        public void RESP_12_test_02(){

            ineligible.add(1);
            ineligible.add(3);
            game.giveHotseat(game.getNextPlayer(ineligible));
            assertEquals(2, game.getCurrentPlayer());
            game.giveHotseat(game.getNextPlayer(ineligible));
            assertEquals(0, game.getCurrentPlayer());

        }
    }

    @Nested
    class RESP13Tests{
        EventCard quest, prosperity,queens, plague;
        @BeforeEach
        public void test_setup(){

            quest = new EventCard(3);
            prosperity = new EventCard(EventCard.Type.Prosperity);
            queens = new EventCard(EventCard.Type.QueensFavor);
            plague = new EventCard(EventCard.Type.Plague);

            //put one of each type of card at top of deck
            game.overwriteEventDeck(0,quest);
            game.overwriteEventDeck(1,prosperity);
            game.overwriteEventDeck(2,queens);
            game.overwriteEventDeck(3, plague);
        }

        @Test
        @DisplayName("Event deck correctly reduces in size")
        public void RESP13_test_01(){
            int before = game.getEventDeckSize();
            game.pullNextEventCard();
            int after = game.getEventDeckSize();

            assertEquals(before-1, after, "Event deck should be smaller in size.");
        }

        @Test
        @DisplayName("Discard pile should grow.")
        public void RESP13_test_02(){
            int before = game.getEventDiscardSize();
            game.pullNextEventCard();
            int after = game.getEventDiscardSize();

            assertEquals(before+1, after, "Event card discard pile should be larger.");
        }

        @Test
        @DisplayName("Current event card is properly set")
        public void RESP13_test_03(){
            game.pullNextEventCard();
            assertEquals(new EventCard(3).getType(),game.getCurrentEvent(), "Current event should be quest." );
        }

        @Test
        @DisplayName("Each type of event card is properly displayed")
        public void RESP13_test_04(){
            game.pullNextEventCard();
            assertTrue(output.toString().contains("A Q3 card was drawn."), "Quest card should be properly displayed.");

            game.pullNextEventCard();
            assertTrue(output.toString().contains("A Prosperity card was drawn.  All players draw 2 cards."), "Prosperity card should be properly displayed.");

            game.pullNextEventCard();
            assertTrue(output.toString().contains("A Queen's Favor card was drawn.  P1 draws 2 cards."), "Queen's Favor card should be properly displayed.");

            //also tests if current player is being displayed
            input.println();
            game.giveHotseat(1);
            game.pullNextEventCard();
            assertTrue(output.toString().contains("A Plague card was drawn.  P2 loses 2 shields."), "Plague card should be properly displayed.");

        }

    }

    @Nested
    class RESP14Tests{
        @BeforeEach
        public void test_setup(){
            input.println();
            game.giveHotseat(0);
            game.overwriteEventDeck(0,new EventCard(EventCard.Type.Plague));
        }

        @Test
        @DisplayName("Test shields are by two correctly")
        public void RESP_14_test_01(){
            game.getPlayer(0).overrideShields(3);
            game.pullNextEventCard();
            game.handleCurrentEvent();

            assertEquals(1,game.getPlayer(0).getShields(), "Shields should have been reduced from 3 to 1.");
        }

        @Test
        @DisplayName("Test shields aren't reduced below 0")
        public void RESP_14_test_02(){
            game.getPlayer(0).overrideShields(1);
            game.pullNextEventCard();
            game.handleCurrentEvent();
            assertEquals(0, game.getPlayer(0).getShields(), "Shields should have been reduced from 1 to 0.");
        }
    }
    @Nested
    class RESP15Tests{
        @BeforeEach
        public void test_setup(){
            input.println();
            game.giveHotseat(0);
            game.overwriteEventDeck(0, new EventCard(EventCard.Type.QueensFavor));

        }

        @Test
        @DisplayName("Test player's hand size increases")
        public void RESP_15_test_01(){
            for(int i = 0; i<5; i++) {
                game.getPlayer(0).addToHand(game.drawAdventureCard());
            }
            game.pullNextEventCard();
            game.handleCurrentEvent();
            assertEquals(7, game.getPlayer(0).getHandSize(), "Player hand size should be 7");
        }

        @Test
        @DisplayName("Test player's hand is trimmed if over 12")
        public void RESP_15_test_02(){
            for(int i = 0; i<11; i++) {
                game.getPlayer(0).addToHand(game.drawAdventureCard());
            }
            input.println("1\n1\n1\n");
            game.pullNextEventCard();
            game.handleCurrentEvent();
            assertEquals(12, game.getPlayer(0).getHandSize(),"Player hand size should be 12");
        }
    }

    @Nested
    class RESP16Tests{

        @BeforeEach
        public void test_setup(){
            input.println("1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n");
            game.giveHotseat(0);
            game.overwriteEventDeck(0, new EventCard(EventCard.Type.Prosperity));
            game.dealCards(10);
            game.getPlayer(0).addToHand(game.drawAdventureCard());
            game.getPlayer(1).addToHand(game.drawAdventureCard());
            game.getPlayer(3).addToHand(game.drawAdventureCard());
            game.getPlayer(3).addToHand(game.drawAdventureCard());
            game.pullNextEventCard();
            game.handleCurrentEvent();
        }

        @Test
        @DisplayName("Check each player draws 2 cards and discards if needed")
        public void RESP_16_test_01(){
            assertEquals(12, game.getPlayer(0).getHandSize(), "P1 should have drawn 2 cards to reach 12, then trimmed down to 12");
            assertEquals(12, game.getPlayer(1).getHandSize(), "P2 should have drawn 2 cards to reach 13, then trimmed down to 12");
            assertEquals(12, game.getPlayer(2).getHandSize(), "P3 should have drawn 2 cards to reach 12");
            assertEquals(12, game.getPlayer(3).getHandSize(), "P4 should have drawn 2 cards to reach 14, then trimmed down to 12");
        }

        @Test
        @DisplayName("Check every player who was prompted to take a turn trimmed their hand")
        public void RESP_16_test_02(){
            assertTrue(output.toString().contains("It is now P1's turn."));
            assertTrue(output.toString().contains("It is now P2's turn."));
            assertFalse(output.toString().contains("It is now P3's turn."));
            assertTrue(output.toString().contains("It is now P4's turn."));
        }
    }

    @Nested
    class RESP17Tests{
        @BeforeEach
        public void test_setup(){
            input.println();
            game.giveHotseat(1); // P2->P3->P4->P1
            game.overwriteEventDeck(0, new EventCard(4));
            game.getPlayer(1).addToHand(new AdventureCard(AdventureCard.Type.Foe,5)); //will go over max had size, but outside scope of this test
            game.pullNextEventCard();
        }

        @Test
        @DisplayName("Test if sponsor is found when first player accepts")
        public void RESP_17_test_01(){
            input.println("y\n"); //first player accepts
            game.handleCurrentEvent();

            assertTrue(output.toString().contains("P2 has agreed to sponsor the quest."), "P2 should have sponsored the quest");
        }

        @Test
        @DisplayName("Test if sponsor is found when last player accepts")
        public void RESP_17_test_02(){
            input.println("n\n\n\nn\n\n\nn\n\n\ny\n"); //fourth player accepts
            game.handleCurrentEvent();

            assertTrue(output.toString().contains("P1 has agreed to sponsor the quest."),"P1 should have sponsored the quest");
        }

        @Test
        @DisplayName("Test if game moves in if everyone declines")
        public void RESP_17_test_03(){
            input.println("n\n\n\nn\n\n\nn\n\n\nn\n\n\n"); // all decline
            game.handleCurrentEvent();

            assertTrue(output.toString().contains("All players have declined to sponsor. Moving on..."), "No one should have sponsored the quest");
        }

        @Test
        @DisplayName("Test if player's hand is displayed correctly")
        public void RESP_17_test_04(){
            input.println("y\n\n\n");
            game.handleCurrentEvent();

            assertTrue(output.toString().contains("1 - F5"), "P2 should have a F5 in slot 1");
        }
    }

    @Nested
    class RESP18Tests{

        List<AdventureCard> actualHand;

        @BeforeEach
        public void test_setup(){
            input.println();
            game.giveHotseat(0);

            Player player = game.getPlayer(0);

            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,20));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,40));
            player.addToHand(new AdventureCard(AdventureCard.Type.Dagger,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Dagger,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Horse,10));

            //assume P1 is sponsor
            input.println("1\n7\n8\nQuit\n9\n");
            actualHand = game.getSponsorChoices(1,0).getCards();
        }

        @Test
        @DisplayName("Test that input stops at 'Quit'")
        public void RESP_18_test_01(){
            ArrayList<AdventureCard> expectedHand = new ArrayList<>();
            expectedHand.add(new AdventureCard(AdventureCard.Type.Foe,5));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Dagger,5));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Sword,10));

            assertEquals(expectedHand, actualHand);
        }

        @Test
        @DisplayName("Test cards are removed from hand after selection")
        public void RESP_18_test_02(){
            assertEquals(9,game.getPlayer(0).getHandSize());
        }

        @Test
        @DisplayName("Test player is prompts are correct")
        public void RESP_18_test_03(){
            //display hand check
            assertTrue(output.toString().contains("1 - F5"));
            assertTrue(output.toString().contains("7 - D5"));
            assertTrue(output.toString().contains("8 - S10"));
            assertTrue(output.toString().contains("9 - H10"));


            assertTrue(output.toString().contains("Choose a card to add to the stage (or type \"Quit\" to stop)."));


        }
    }

    @Nested
    class RESP19Tests{

        List<AdventureCard> actualHand;

        @BeforeEach
        public void test_setup(){
            input.println();
            game.giveHotseat(0);

            Player player = game.getPlayer(0);

            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,20));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,40));
            player.addToHand(new AdventureCard(AdventureCard.Type.Dagger,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Dagger,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Horse,10));

            //assume P1 is sponsor
            input.println("1\n5\n7\n7\n8\nQuit\n");//add F5,F20,D5,D5,S10
            actualHand = game.getSponsorChoices(1,0).getCards();
        }

        @Test
        @DisplayName("Game accepts only non-duplicate cards and single foe cards")
        public void RESP_19_test_01(){
            ArrayList<AdventureCard> expectedHand = new ArrayList<>();
            expectedHand.add(new AdventureCard(AdventureCard.Type.Foe,5));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Dagger,5));
            expectedHand.add(new AdventureCard(AdventureCard.Type.Sword,10));


            assertEquals(expectedHand,actualHand);
        }

        @Test
        @DisplayName("Game prompts player that card is invalid with reason")
        public void RESP_19_test_02(){
            assertTrue(output.toString().contains("F20 is invalid.  Only one foe card allowed."));
            assertTrue(output.toString().contains("D5 is invalid.  No duplicates allowed."));
        }
    }

    @Nested
    class RESP20Tests{

        @BeforeEach
        public void test_setup(){
            input.println();
            game.giveHotseat(0);

            Player player = game.getPlayer(0);

            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,20));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,40));
            player.addToHand(new AdventureCard(AdventureCard.Type.Dagger,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Dagger,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Horse,10));

            //assume P1 is sponsor
            input.println("12\n1\n7\nQuit\n");//add stage cards out of order
            ArrayList<AdventureCard> actualHand = game.getSponsorChoices(1,0).getCards();
        }

        @Test
        @DisplayName("Test that stage contents are sorted and displayed correctly")
        public void RESP_20_test_01(){
            assertTrue(output.toString().contains("Current stage cards:"));
            assertTrue(output.toString().contains("Current stage cards: H10"));
            assertTrue(output.toString().contains("Current stage cards: F5 H10"));
            assertTrue(output.toString().contains("Current stage cards: F5 D5 H10"));
        }

        @Test
        @DisplayName("Test that stage total value is calculated and displayed correctly")
        public void RESP_20_test_02(){
            assertTrue(output.toString().contains("Current stage value: 0"));
            assertTrue(output.toString().contains("Current stage value: 10"));
            assertTrue(output.toString().contains("Current stage value: 15"));
            assertTrue(output.toString().contains("Current stage value: 20"));
        }

    }

    @Nested
    class RESP21Tests{
        Quest quest;
        @BeforeEach
        public void test_setup(){
            input.println();
            game.giveHotseat(0);

            game.overwriteEventDeck(0,new EventCard(4));
            game.pullNextEventCard();

            Player player = game.getPlayer(0);

            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,20));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,40));
            player.addToHand(new AdventureCard(AdventureCard.Type.Dagger,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Dagger,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Horse,10));


            //add F5, then D5 for first stage
            input.println("1\n7\nQuit");

            //try to add F10, then quit, fail, then add S10, then quit for second stage
            input.println("2\nQuit\n7\nQuit");

            //try to add empty stage, fail, then add F15,H10
            input.println("Quit\n2\n7\nQuit");

            //add F40
            input.println("4\nQuit");

            quest = game.buildQuest();
        }

        @Test
        @DisplayName("Test if quest has correct amount of stages")
        public void RESP_21_test_01(){
            assertEquals(4, quest.getStages().size());
        }

        @Test
        @DisplayName("Test if quest has expected stage values")
        public void RESP_21_test_02(){
            List<Integer> stageValues = new ArrayList<>();
            for(Stage s : quest.getStages()){
                stageValues.add(s.getValue());
            }
            assertEquals(List.of(10,20,25,40), stageValues);
        }

        @Test
        @DisplayName("Test if user is correctly prompted for errors")
        public void RESP_21_test_03(){
            assertFalse(output.toString().contains("Stage 1 cannot be completed."));
            assertTrue(output.toString().contains("Stage 2 cannot be completed.  Insufficient value for this stage."));
            assertTrue(output.toString().contains("Stage 3 cannot be completed.  A stage cannot be empty."));
            assertFalse(output.toString().contains("Stage 4 cannot be completed."));
        }

    }

    @Nested
    class RESP22Tests{
        Player player;
        Attack attack;
        @BeforeEach
        public void test_setup(){
            input.println();
            game.giveHotseat(0);

            player = game.getPlayer(0);
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Foe,15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Dagger,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Dagger,5));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Sword,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.Horse,10));
            player.addToHand(new AdventureCard(AdventureCard.Type.BattleAxe, 15));
            player.addToHand(new AdventureCard(AdventureCard.Type.Excalibur, 30));



            input.println("4\n6\n8\nQuit\n4"); //add D5,S10,E30

            attack = game.buildAttack();

        }

        @Test
        @DisplayName("Test attack contents match expectations")
        public void RESP_22_test_01(){
            ArrayList<AdventureCard> expectedCards = new ArrayList<>();
            expectedCards.add(new AdventureCard(AdventureCard.Type.Dagger,5));
            expectedCards.add(new AdventureCard(AdventureCard.Type.Sword,10));
            expectedCards.add(new AdventureCard(AdventureCard.Type.Excalibur,30));

            assertEquals(expectedCards, attack.getCards());
        }

        @Test
        @DisplayName("Test players hand shrinks accordingly")
        public void RESP_22_test_02(){
            assertEquals(7, player.getHandSize());
        }


        @Test
        @DisplayName("Test player is prompted correctly")
        public void RESP_22_test_03(){

            //check hand display
            assertTrue(output.toString().contains("10 - E30"));
            assertTrue(output.toString().contains("9 - E30"));
            assertTrue(output.toString().contains("8 - E30"));
            assertTrue(output.toString().contains("7 - B15"));

            assertTrue(output.toString().contains("Choose a card to add to your attack:"));
        }

    }
}

