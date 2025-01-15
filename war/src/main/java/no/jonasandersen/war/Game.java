package no.jonasandersen.war;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game extends EventSourcedAggregate {

  private UUID id;
  private Deck deck;
  private List<Player> players = new ArrayList<>(2);

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
      case PlayerJoinedEvent playerJoinedEvent -> {
        if (this.id.equals(playerJoinedEvent.uuid())) {
          players.add(new Player(playerJoinedEvent.playerName()));
        }
      }
    }
  }

  public List<Player> players() {
    return List.copyOf(players);
  }
}
