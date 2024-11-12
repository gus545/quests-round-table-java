import org.example.AdventureCard;
import org.example.EventCard;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RiggedDecks {


    static List<AdventureCard> testAdventureDeck1 = new ArrayList<>(List.of(
            new AdventureCard(5),
            new AdventureCard(5),
            new AdventureCard(15),
            new AdventureCard(15),
            new AdventureCard(AdventureCard.Type.Dagger),
            new AdventureCard(AdventureCard.Type.Sword),
            new AdventureCard(AdventureCard.Type.Sword),
            new AdventureCard(AdventureCard.Type.Horse),
            new AdventureCard(AdventureCard.Type.Horse),
            new AdventureCard(AdventureCard.Type.BattleAxe),
            new AdventureCard(AdventureCard.Type.BattleAxe),
            new AdventureCard(AdventureCard.Type.Lance),
            new AdventureCard(5),
            new AdventureCard(5),
            new AdventureCard(15),
            new AdventureCard(15),
            new AdventureCard(40),
            new AdventureCard(AdventureCard.Type.Dagger),
            new AdventureCard(AdventureCard.Type.Sword),
            new AdventureCard(AdventureCard.Type.Horse),
            new AdventureCard(AdventureCard.Type.Horse),
            new AdventureCard(AdventureCard.Type.BattleAxe),
            new AdventureCard(AdventureCard.Type.BattleAxe),
            new AdventureCard(AdventureCard.Type.Excalibur),
            new AdventureCard(5),
            new AdventureCard(5),
            new AdventureCard(5),
            new AdventureCard(15),
            new AdventureCard(AdventureCard.Type.Dagger),
            new AdventureCard(AdventureCard.Type.Sword),
            new AdventureCard(AdventureCard.Type.Sword),
            new AdventureCard(AdventureCard.Type.Sword),
            new AdventureCard(AdventureCard.Type.Horse),
            new AdventureCard(AdventureCard.Type.Horse),
            new AdventureCard(AdventureCard.Type.BattleAxe),
            new AdventureCard(AdventureCard.Type.Lance),
            new AdventureCard(5),
            new AdventureCard(15),
            new AdventureCard(15),
            new AdventureCard(40),
            new AdventureCard(AdventureCard.Type.Dagger),
            new AdventureCard(AdventureCard.Type.Dagger),
            new AdventureCard(AdventureCard.Type.Sword),
            new AdventureCard(AdventureCard.Type.Horse),
            new AdventureCard(AdventureCard.Type.Horse),
            new AdventureCard(AdventureCard.Type.BattleAxe),
            new AdventureCard(AdventureCard.Type.Lance),
            new AdventureCard(AdventureCard.Type.Excalibur),
            new AdventureCard(30),
            new AdventureCard(AdventureCard.Type.Sword),
            new AdventureCard(AdventureCard.Type.BattleAxe),
            new AdventureCard(10),
            new AdventureCard(AdventureCard.Type.Lance),
            new AdventureCard(AdventureCard.Type.Lance),
            new AdventureCard(AdventureCard.Type.BattleAxe),
            new AdventureCard(AdventureCard.Type.Sword),
            new AdventureCard(30),
            new AdventureCard(AdventureCard.Type.Lance)
    ));

    private static final Map<Integer,List<AdventureCard>> adventureDecks = Map.of(
            1,testAdventureDeck1
    );

    private static final Map<Integer,List<EventCard>> eventDecks = Map.of(
        1, new ArrayList<>(List.of(new EventCard(4)))
    );


    public static List<AdventureCard> getTestAdventureDeck(int i){
        return adventureDecks.get(i);
    }

    public static List<EventCard> getTestEventDeck(int i){
        return eventDecks.get(i);
    }
}
