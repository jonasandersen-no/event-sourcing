package no.jonasandersen.event.war.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import no.jonasandersen.event.common.EventSourcedAggregate;

public class Game extends EventSourcedAggregate<GameEvent> {

  private UUID id;
  private Deck deck;
  private List<Player> players = new ArrayList<>(2);

  private Game() {
  }

  private Game(UUID id) {
    enqueue(new GameCreatedEvent(id));
  }

  public static Game create(UUID gameId) {
    return new Game(gameId);
  }

  public static Game reconstruct(List<GameEvent> events) {
    Game game = new Game();
    for (GameEvent event : events) {
      game.enqueue(event);
    }
    return game;
  }

  public UUID id() {
    return id;
  }

  public Deck deck() {
    return deck;
  }

  void addPlayer(String playerName) {
    if (players.size() >= 2) {
      throw new GameIsFullException();
    }
    enqueue(new PlayerJoinedEvent(id, playerName));
  }

  public List<Player> getPlayers() {
    return List.copyOf(players);
  }

  @Override
  protected void apply(GameEvent event) {
    switch (event) {
      case DeckCreatedEvent deckCreatedEvent -> {
        deck = new Deck(deckCreatedEvent.cards());
      }
      case GameCreatedEvent gameCreatedEvent -> {
        id = gameCreatedEvent.id();
      }
      case PlayerJoinedEvent playerJoinedEvent -> {
        if (this.id.equals(playerJoinedEvent.uuid())) {
          players.add(new Player(playerJoinedEvent.playerName()));
        }
      }
      case DealCardEvent dealCardEvent -> {
        Card card = deck.draw();
        Player player = getPlayer(dealCardEvent.playerName());
        player.addCard(card);
      }
    }
  }

  public void dealCard(String playerName) {
    enqueue(new DealCardEvent(playerName));
  }

  public Player getPlayer(String playerName) {
    for (Player player : players) {
      if (player.getName().equals(playerName)) {
        return player;
      }
    }
    throw new PlayerNotFoundException(playerName);
  }

  public void join(String playerName) {
    enqueue(new PlayerJoinedEvent(this.id, playerName));
  }

  public boolean hasJoined(String playerName) {
    return getPlayer(playerName) != null;
  }

  public boolean canJoin() {
    return getPlayers().size() < 2;
  }

  public void createDeck(List<Card> cards) {
    enqueue(new DeckCreatedEvent(cards));
  }
}
