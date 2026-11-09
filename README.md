# Quests of the Round Table (Console Edition)

A playable, four-player, hot-seat console version of the Arthurian card game *Quests of the Round Table*, written in Java with a JUnit 5 test suite of 83 tests mapped to the game's requirements.

Built as a software engineering course project at Carleton University.

## Gameplay

Four players share one terminal and take turns. Each turn begins by drawing an event card:

- **Quest cards (2 to 5 stages):** The current player can sponsor the quest. If they decline, the offer passes around the table. The sponsor builds each stage from a foe plus optional weapons, and each stage must be stronger than the one before it. The other players choose stage by stage whether to keep going. Anyone who continues draws a card and then builds an attack from weapon cards. Attacks that beat the stage advance, and players who survive every stage earn one shield per stage.
- **Plague:** The current player loses 2 shields.
- **Queen's Favor:** The current player draws 2 adventure cards.
- **Prosperity:** Every player draws 2 adventure cards.

Hands are capped at 12 cards. Players over the limit are prompted to discard. The first player or players to reach **7 shields** win.

### Decks
| Deck | Contents |
|---|---|
| Adventure (100 cards) | 50 foes (values 5–70) and 50 weapons: Dagger 5, Horse 10, Sword 10, Battle-Axe 15, Lance 20, Excalibur 30 |
| Event (17 cards) | 12 quests (2–5 stages) and 5 events: Plague, Queen's Favor ×2, Prosperity ×2 |

## Design

| Class | Responsibility |
|---|---|
| `Main` | Game setup, turn loop, hot-seat handoff, event handling, quest flow |
| `Deck<T>` | Generic deck with draw, shuffle, discard and test rigging |
| `AdventureCard` / `EventCard` | Card types and values |
| `AdventureCardComparator` | Sorts hands with foes first, then weapons by value |
| `Player` | Hand, shields, and discarding |
| `Quest` / `Stage` | Quest structure, sponsor stage validation, participant tracking |
| `Attack` | Builds a player's attack and enforces its rules, such as no foes and no duplicate weapons |

Input and output go through an injected `PrintWriter` and `Scanner` instead of `System.out` and `System.in` directly. Because of that, the tests can script player input and assert on the exact console output.

## Testing

The suite has **83 JUnit 5 tests**, organized by requirement (`RESP_01` through `RESP_31`). It covers deck composition, shuffling, dealing, hand sorting and trimming, turn order, event effects, sponsor and stage validation, attack building, stage resolution, and win detection.

Deterministic scenarios are set up with deck and hand rigging methods (`rigAdventureDeck`, `rigEventDeck`, `rigPlayerHand`), so complete quests can be replayed and checked from start to finish.

## Running it

Requires **Java 22** and **Maven**.

```bash
# Run the tests
mvn test

# Build and play
mvn compile
java -cp target/classes org.example.Main
```

## Tech
Java 22 · Maven · JUnit 5
