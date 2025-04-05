package no.jonasandersen.event.war.domain;

import static org.assertj.core.api.Assertions.assertThat;

import no.jonasandersen.event.war.port.DeckGenerator;
import no.jonasandersen.event.war.port.DefaultDeckGenerator;
import org.junit.jupiter.api.Test;

public class DeckTest {

  @Test
  void generateDeckCreates52Cards() {
    Deck deck = new DefaultDeckGenerator().generateDeck();

    assertThat(deck.cards())
        .hasSize(52)
        .doesNotHaveDuplicates();
  }
}
