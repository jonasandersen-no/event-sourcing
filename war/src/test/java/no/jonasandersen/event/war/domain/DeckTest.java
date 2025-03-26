package no.jonasandersen.event.war.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DeckTest {

  @Test
  void generateDeckCreates52Cards() {
    Deck deck = Deck.generateRandomDeck();

    assertThat(deck.cards())
        .hasSize(52)
        .doesNotHaveDuplicates();
  }
}
