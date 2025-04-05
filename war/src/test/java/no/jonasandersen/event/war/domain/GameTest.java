package no.jonasandersen.event.war.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.UUID;
import no.jonasandersen.event.war.port.InOrderDeckGenerator;
import org.junit.jupiter.api.Test;

class GameTest {

  @Test
  void gameContainsDeck() {
    UUID id = UUID.randomUUID();
    Game game = Game.create(id);

    assertThat(game.id())
        .isNotNull();
  }

  @Test
  void deckContainsOneCard() {
    Game game = Game.create(UUID.randomUUID());

    game.createDeck(List.of(new Card(Rank.ACE, Suit.CLUBS)));

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
    UUID id = UUID.randomUUID();
    Game game = Game.create(id);

    assertThat(game.canJoin()).isTrue();

    game.join("Player 1");

    assertThat(game.hasJoined("Player 1"))
        .isTrue();
  }

  @Test
  void multiplePlayersCanJoinEmptyGame() {
    UUID id = UUID.randomUUID();
    Game game = Game.reconstruct(List.of(
        new GameCreatedEvent(id),
        new PlayerJoinedEvent(id, "Player 1")
    ));

    assertThat(game.canJoin()).isTrue();

    game.join("Player 2");

    assertThat(game.hasJoined("Player 2")).isTrue();

  }

  @Test
  void maxTwoPlayersCanJoinEmptyGame() {

    UUID id = UUID.randomUUID();
    Game game = Game.reconstruct(List.of(
        new GameCreatedEvent(id),
        new PlayerJoinedEvent(id, "Player 1"),
        new PlayerJoinedEvent(id, "Player 2")));

    assertThat(game.canJoin()).isFalse();

    assertThatThrownBy(() -> game.addPlayer("Player 3"))
        .isInstanceOf(GameIsFullException.class)
        .hasMessage("The game is full");

    assertThat(game.getPlayers())
        .isNotNull()
        .hasSize(2)
        .containsExactly(new Player("Player 1"), new Player("Player 2"));
  }

  @Test
  void dealCardToPlayer() {
    Game game = createGameWithDeck();

    game.addPlayer("Player 1");

    game.dealCard("Player 1");

    Player player = game.getPlayer("Player 1");

    assertThat(player.getCards())
        .hasSize(1);

    assertThat(game.deck().isFull())
        .isFalse();
  }

  @Test
  void dealCardToBothPlayers() {
    Game game = createGameWithDeck();
    game.addPlayer("Player 1");
    game.addPlayer("Player 2");

    game.dealCard("Player 1");
    game.dealCard("Player 2");

    Player player1 = game.getPlayer("Player 1");
    Player player2 = game.getPlayer("Player 2");

    assertThat(player1.getCards())
        .hasSize(1);

    assertThat(player2.getCards())
        .hasSize(1);

    assertThat(game.deck().size())
        .isEqualTo(50);

  }

  private static Game createGameWithDeck() {
    UUID gameId = UUID.randomUUID();
    return Game.reconstruct(List.of(
        new GameCreatedEvent(gameId),
        new DeckCreatedEvent(new InOrderDeckGenerator().generateDeck().cards())
    ));
  }
}