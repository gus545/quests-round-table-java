Feature: Game of Quests
  Scenario: A1_Scenario
    Given a new game with test adventure deck 1 and test event deck 1
    When an event card is drawn
    Then the event should be a "Quest" card
    And P2 accepts to sponsor
    And P2 builds the following stages:
      | F5  | H10 |     |
      | F15 | S10 |     |
      | F15 | D5  | B15 |
      | F40 | B15 |     |
    And the following players decide to participate: "1, 3, 4"
    And the following attacks are built:
      | D5 | S10 |
      | S10 | D5 |
      | D5 | H10 |
    And the attacks are resolved and the following players advance: "1, 3, 4"
    And the following players decide to participate: "1, 3, 4"
    And the following attacks are built:
      | H10 | S10 |
      | B15 | S10 |
      | H10 | B15 |
    And the attacks are resolved and the following players advance: "3, 4"
    And P1 has 0 shields
    And P1 has the following hand: "F5 F10 F15 F15 F30 H10 B15 B15 L20"
    And the following players decide to participate: "3, 4"
    And the following attacks are built:
      | L20 | H10 | S10 |
      | B15 | S10 | L20 |
    And the attacks are resolved and the following players advance: "3, 4"
    And the following players decide to participate: "3, 4"
    And the following attacks are built:
      | B15 | H10 | L20 |     |
      | D5  | S10 | L20 | E30 |
    And the attacks are resolved and the following players complete the quest: "4"
    And P3 has 0 shields
    And P4 has 4 shields
    And P3 has the following hand: "F5 F5 F15 F30 S10"
    And P4 has the following hand: "F15 F15 F40 L20"
    And P2 has 12 cards in hand
