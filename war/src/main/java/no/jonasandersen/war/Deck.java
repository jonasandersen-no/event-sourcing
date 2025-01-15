package no.jonasandersen.war;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

  private List<Card> cards;

  public Deck() {
  }

  public Deck(List<Card> cards) {
    this.cards = cards;
  }

  public static Deck generateRandomDeck() {
    List<Card> cards = new ArrayList<>();
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        cards.add(new Card(rank, suit));
      }
    }

    Collections.shuffle(cards);
    return new Deck(cards);
  }

  public boolean isFull() {
    return false;
  }

  public List<Card> cards() {
    return List.copyOf(cards);
  }
}
