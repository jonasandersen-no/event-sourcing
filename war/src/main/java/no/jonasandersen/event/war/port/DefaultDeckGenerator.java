package no.jonasandersen.event.war.port;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import no.jonasandersen.event.war.domain.Card;
import no.jonasandersen.event.war.domain.Deck;
import no.jonasandersen.event.war.domain.Rank;
import no.jonasandersen.event.war.domain.Suit;

public class DefaultDeckGenerator implements DeckGenerator {

  @Override
  public Deck generateDeck() {
    List<Card> cards = new ArrayList<>();
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        cards.add(new Card(rank, suit));
      }
    }

    Collections.shuffle(cards);
    return new Deck(cards);
  }
}
