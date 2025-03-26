package no.jonasandersen.event.war.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

  private final List<Card> cards = new ArrayList<>();

  public Deck(List<Card> cards) {
    this.cards.addAll(cards);
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
    return cards.size() == 52;
  }

  public List<Card> cards() {
    return List.copyOf(cards);
  }

  public Card draw() {
    return cards.removeFirst();
  }

  public int size() {
    return cards.size();
  }
}
