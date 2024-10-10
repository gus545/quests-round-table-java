import org.example.AdventureCard;
import org.example.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private Main game;
    private int deckSize;

    @BeforeEach
    public void RESP_01_test_setup() {
        game = new Main();
        game.initializeAdventureDeck();
        deckSize = game.getAdventureDeckSize();
    }

    @Test
    @DisplayName("Check if adventure deck size is 100")
    void RESP_01_test_01(){
        assertEquals(100, deckSize, "There should be 100 adventure cards.");
    }

    @Test
    @DisplayName("Check if there are 50 foe cards and 50 weapon cards")
    void RESP_01_test_02(){
        int foeCount = 0;
        int weaponCount = 0;


        List<AdventureCard.Type> weaponTypes = new ArrayList<>();
        weaponTypes.add(AdventureCard.Type.Dagger);
        weaponTypes.add(AdventureCard.Type.Horse);
        weaponTypes.add(AdventureCard.Type.Sword);
        weaponTypes.add(AdventureCard.Type.BattleAxe);
        weaponTypes.add(AdventureCard.Type.Lance);
        weaponTypes.add(AdventureCard.Type.Excalibur);


        for(int i = 0; i < deckSize; i++){
            AdventureCard newCard = game.drawAdventureCard();
            if(newCard.getType() == AdventureCard.Type.Foe){
                foeCount++;
            }
            if(weaponTypes.contains(newCard.getType())){
                weaponCount++;
            }
        }

        assertEquals(50, foeCount, "There should be 50 foe cards in the deck");
        assertEquals(50, weaponCount, "There should be 50 weapon cards in the deck");
    }

    @Test
    @DisplayName("Check if there's the correct number of each type of foe card")
    void RESP_01_test_03(){
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

        for (int i = 0; i<deckSize; i++){
            AdventureCard newCard = game.drawAdventureCard();
            if(newCard.getType()==AdventureCard.Type.Foe){
                switch (newCard.getValue()){
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
    void RESP_01_test_04(){
        int daggerCount = 0;
        int horseCount = 0;
        int swordCount = 0;
        int battleAxeCount = 0;
        int lanceCount = 0;
        int excaliburCount = 0;

        for (int i = 0; i < deckSize; i++) {
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
