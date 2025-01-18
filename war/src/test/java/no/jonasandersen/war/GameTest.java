package no.jonasandersen.war;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class GameTest {

  @Test
  void gameContainsDeck() {
    UUID id = UUID.randomUUID();
    Game game = new Game();

    game.apply(new GameStartedEvent(id));

    assertThat(game.id())
        .isNotNull();
  }

  @Test
  void deckContainsOneCard() {
    Game game = new Game();

    game.apply(new GameStartedEvent(UUID.randomUUID()));
    game.apply(new DeckCreatedEvent(List.of(new Card(Rank.ACE, Suit.CLUBS))));

    Deck deck = game.deck();

    assertThat(deck)
        .isNotNull();
    assertThat(deck.cards())
        .isNotNull()
        .hasSize(1)
        .contains(new Card(Rank.ACE, Suit.CLUBS));
  }

  @Test
  void playerCanJoinEmptyGame() {
    Game game = new Game();

    UUID id = UUID.randomUUID();
    game.apply(new GameStartedEvent(id));
    game.apply(new PlayerJoinedEvent(id, "Player 1"));

    assertThat(game.players())
        .isNotNull()
        .hasSize(1)
        .contains(new Player("Player 1"));
  }

  @Test
  void multiplePlayersCanJoinEmptyGame() {
    Game game = new Game();

    UUID id = UUID.randomUUID();
    game.apply(new GameStartedEvent(id));
    game.apply(new PlayerJoinedEvent(id, "Player 1"));
    game.apply(new PlayerJoinedEvent(id, "Player 2"));

    assertThat(game.players())
        .isNotNull()
        .hasSize(2)
        .containsExactly(new Player("Player 1"), new Player("Player 2"));
  }

  @Test
  void maxTwoPlayersCanJoinEmptyGame() {
    Game game = new Game();

    UUID id = UUID.randomUUID();
    List<GameEvent> events = List.of(
        new GameStartedEvent(id),
        new PlayerJoinedEvent(id, "Player 1"),
        new PlayerJoinedEvent(id, "Player 2"));

    game.reconstruct(events);

    game.apply(new PlayerJoinedEvent(id, "Player 2"));

    game.addPlayer("Player 3");

    assertThat(game.players())
        .isNotNull()
        .hasSize(2)
        .containsExactly(new Player("Player 1"), new Player("Player 2"));
  }
}