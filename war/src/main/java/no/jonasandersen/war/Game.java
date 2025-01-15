package no.jonasandersen.war;

import java.util.UUID;

public class Game extends EventSourcedAggregate {

  private UUID id;
  private Deck deck;

  public UUID id() {
    return id;
  }

  public Deck deck() {
    return deck;
  }

  @Override
  protected void apply(GameEvent event) {
    switch (event) {
      case DeckCreatedEvent deckCreatedEvent -> {
        deck = new Deck(deckCreatedEvent.cards());
      }
      case GameStartedEvent gameStartedEvent -> {
        id = gameStartedEvent.id();
      }
    }
  }
}
