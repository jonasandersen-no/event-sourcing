package no.jonasandersen.war;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class DeckTest {

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

  private List<Card> generateDeck() {
    return List.of(new Card(Rank.ACE, Suit.CLUBS));
  }

  @Test
  void generateDeckCreates52Cards() {
    Deck deck = Deck.generateRandomDeck();

    System.out.println(deck.cards());
    assertThat(deck.cards())
        .hasSize(52);
  }
}
