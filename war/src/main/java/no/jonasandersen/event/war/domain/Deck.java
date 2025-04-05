package no.jonasandersen.event.war.domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {

  private final List<Card> cards = new ArrayList<>();

  public Deck(List<Card> cards) {
    this.cards.addAll(cards);
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
