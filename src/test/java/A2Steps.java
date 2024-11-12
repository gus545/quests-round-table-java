import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class A2Steps {
    private Main game;
    private StringWriter output;
    private PrintWriter input;
    private int currentStage;
    private List<Attack> currentAttacks;


    @Given("a new game with test adventure deck {int} and test event deck {int}")
    public void a_new_game(int aDeck, int eDeck) throws IOException {
        output = new StringWriter();

        PipedOutputStream outputStream = new PipedOutputStream();
        PipedInputStream inputStream = new PipedInputStream(outputStream);
        input = new PrintWriter(outputStream, true);

        game = new Main(new PrintWriter(output), new Scanner(inputStream), RiggedDecks.getTestAdventureDeck(aDeck), RiggedDecks.getTestEventDeck(eDeck));

    }

    @When("an event card is drawn")
    public void e_card_drawn(){
        game.pullNextEventCard();
    }

    @Then("the event should be a {string} card")
    public void e_card_should_be(String s){
        assertEquals(s,game.getCurrentEvent().name());
    }

    @Then("P{int} accepts to sponsor")
    public void p_accepts_to_sponsor(int id){

        for(int i=1; i<id;i++) input.println("n\n\n"); //cycle to player who accepts quest

        input.println("y");

        game.findSponsor();

        assertEquals(id,game.getCurrentPlayer()+1);
    }

    @Then("P{int} builds the following stages:")
    public void p_builds_the_following_stages(int id, DataTable d){

        assertEquals(id,game.getCurrentPlayer()+1);

        //convert to list of and filter out null values
        List<List<String>> stages = d.asLists(String.class).stream()
                .map(row -> row.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .toList();

        // slightly modified version of buildQuest()
        Quest quest = new Quest(game.getCurrentStages(),game.getPlayers(),game.getPlayer(game.getCurrentPlayer()));
        for(int i = 1; i<= quest.getNumStages(); i++) {
            String s = getHandInput(stages.get(i-1), game.getPlayer(game.getCurrentPlayer()));
            input.println(s);
            quest.addStage(game.getSponsorChoices(i, quest.getMax()));
        }
        currentStage = 0;
        game.setQuest(quest);

        //assert stages are correct
        for(int i =0; i<quest.getNumStages(); i++) {
            for(String card: stages.get(i)) {
                if(stages.get(i)!=null) assertTrue(quest.getStages().get(i).getStageCardString().contains(card));
            }
        }
    }

    @Then("the following players decide to participate: {string}")
    public void the_following_players_decide_to_participate(String s){
        List<Integer> participatingPlayerIds = Arrays.stream(s.split(", "))
                .map(Integer::parseInt)
                .toList();

        // test validity of participant list
        assertTrue(game.getCurrentQuest().getParticipants().size()>=participatingPlayerIds.size());


        //rig player input
        for(Player p : game.getCurrentQuest().getParticipants()){
            if(participatingPlayerIds.contains(p.getId())) input.println("y");
            else input.println("n");

            //discard lowest value foe card in case of trim
            if(p.getHandSize()==12){
                input.println("1");
            }
            input.println("\n");
        }

        game.promptParticipants();

        List<Integer> expectedIds = game.getCurrentQuest().getParticipants().stream()
                .map(Player::getId)
                .toList();


        assertTrue(expectedIds.containsAll(participatingPlayerIds));
        for(Player p : game.getCurrentQuest().getParticipants()) assertTrue(p.getHandSize()<=12);
    }


    //helper function for rigging player input when picking cards in hand (assumes no duplicates requested)
    private String getHandInput(List<String> desiredCards, Player p){
        StringBuilder out = new StringBuilder();
        List<Integer> desiredIndices = new ArrayList<>();

        for (String s : desiredCards){
            int index = p.getCardIndex(s);
            if(index>=0) desiredIndices.add(index);
        }

        //account for shifting indices as cards are removed
        for (int i =0 ; i<desiredIndices.size(); i++){
            for(int j = i+1; j<desiredIndices.size(); j++){

                if(desiredIndices.get(i)<desiredIndices.get(j)) desiredIndices.set(j,desiredIndices.get(j)-1);
            }
        }

        //construct string
        for (int i : desiredIndices) out.append(i+1).append("\n");

        out.append("quit\n");

        return out.toString();
    }

    @And("the following attacks are built:")
    public void the_following_attacks_are_built(DataTable d) {

        //convert to list of and filter out null values
        List<List<String>> desiredCards = d.asLists(String.class).stream()
                .map(row -> row.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .toList();

        Quest quest = game.getCurrentQuest();

        assertEquals(desiredCards.size(),quest.getParticipants().size());


        List<Integer> participatingPlayerIds = quest.getParticipants().stream()
                .map(Player::getId)
                .toList();

        //rig player input
        input.println();
        for(int i =0; i<desiredCards.size(); i++){
            String cardIndices = getHandInput(desiredCards.get(i),game.getPlayer(participatingPlayerIds.get(i)-1));
            input.println(cardIndices+"\n");
        }

        currentAttacks = game.promptAttacks();

        //assert attacks are correct
        for(int i =0; i<currentAttacks.size(); i++) {
            for(String card: desiredCards.get(i)) {
                if(desiredCards.get(i)!=null) assertTrue(currentAttacks.get(i).getCards().toString().contains(card));
            }
        }
    }


    @Then("the attacks are resolved and the following players advance: {string}")
    public void the_attacks_are_resolved(String s) {

        List<Integer> advancingPlayerIds = Arrays.stream(s.split(", "))
                .map(Integer::parseInt)
                .map(n -> n-1)
                .toList();

        game.resolveStage(currentStage,currentAttacks);

        currentStage++;
        //check if all players are still eligible
        assertTrue(Collections.disjoint(advancingPlayerIds, game.getCurrentQuest().getIneligibleIds()));


    }

    @And("P{int} has {int} shields")
    public void p_has_shields(int id, int shields) {
        assertEquals(game.getPlayer(id-1).getShields(), shields);
    }

    @And("P{int} has the following hand: {string}")
    public void pHasTheFollowingHand(int id, String hand) {
        StringBuilder handString = new StringBuilder();
        for(AdventureCard c : game.getPlayer(id-1).getHand()) handString.append(c.toString()).append(" ");
        assertEquals(hand, handString.toString().trim());
    }

    @And("the attacks are resolved and the following players complete the quest: {string}")
    public void quest_resolved(String s) {
        the_attacks_are_resolved(s);

        //rig player input for trimming hand

        int cardsToDraw = game.getCurrentQuest().getNumStages() + game.getCurrentQuest().getNumCards();
        if(cardsToDraw + game.getCurrentQuest().getSponsor().getHandSize() > 12){
            for(int i = 0; i<cardsToDraw + game.getCurrentQuest().getSponsor().getHandSize(); i++){
                input.println("1");
            }
        }

        game.wrapUpQuest();
        assertEquals(game.getCurrentQuest().getNumStages(), currentStage, "Current stage should be equal to max stages.");
    }


    @And("P{int} has {int} cards in hand")
    public void pHasCardsInHand(int id, int numCards) {
        assertEquals(numCards, game.getPlayer(id-1).getHandSize());
    }
}
