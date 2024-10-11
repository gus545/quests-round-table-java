import org.example.AdventureCard;
import org.example.EventCard;
import org.example.Main;
import org.example.Player;
import org.junit.jupiter.api.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private Main game;
    private int adventureDeckSize;
    private int eventDeckSize;

    @Nested
    class RESP01Tests{
        @BeforeEach
        public void test_setup () {
            game = new Main();
            game.initializeAdventureDeck();
            adventureDeckSize = game.getAdventureDeckSize();
            game.initializeEventDeck();
            eventDeckSize = game.getEventDeckSize();
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
            game = new Main();
            game.initializeEventDeck();
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

            game = new Main();
            game.initializeAdventureDeck();
            adventureDeckSize = game.getAdventureDeckSize();
            game.initializeEventDeck();
            eventDeckSize = game.getEventDeckSize();

            Main game2 = new Main();
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
            game = new Main();
            game.initializeAdventureDeck();
            adventureDeckSize = game.getAdventureDeckSize();
            game.initializePlayers();
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
            game = new Main();
            game.initializePlayers();

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

            StringWriter output = new StringWriter();

            game.displayPlayerHand(0,new PrintWriter(output));

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
            game = new Main();
            game.initializePlayers();
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
        StringWriter output;
        String input;

        @BeforeEach
        public void test_setup(){
            game = new Main();
            game.initializePlayers();
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

            input = "1\n10\n6\n"; //only first 2 inputs should go through
            output = new StringWriter();
            game.trimPlayerHand(0,new Scanner(input), new PrintWriter(output));
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
        String input;
        StringWriter output;
        @BeforeEach
        public void test_setup(){
            game = new Main();
            game.initializePlayers();
            input="\n";
            output = new StringWriter();
            game.giveHotseat(0, new Scanner(input), new PrintWriter(output));
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
            game.giveHotseat(1, new Scanner(input),new PrintWriter(output));
            assertTrue(output.toString().contains("P2"), "Game should display that it is P1's turn");
        }

        @Test
        @DisplayName("Current player changes to correct value")
        public void RESP_08_test_03(){
            assertEquals(0,game.getCurrentPlayer(), "Current player should reflect hotseat assignment");
            game.giveHotseat(3, new Scanner(input),new PrintWriter(output));
            assertEquals(3, game.getCurrentPlayer(), "Current player should reflect hotseat assignment");
        }
    }

    @Nested
    class RESP09Tests{
        StringWriter output;
        String input;
        @BeforeEach
        public void test_setup(){
            game = new Main();
            game.initializePlayers();
            input = "\n";
            output = new StringWriter();
            game.giveHotseat(0,new Scanner(input), new PrintWriter(output));
            game.endCurrentHotseat(new Scanner(input), new PrintWriter(output));
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
}

