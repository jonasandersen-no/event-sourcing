package no.jonasandersen.event.war.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

  private final String playerName;
  private ArrayList<Card> cards = new ArrayList<>();

  public Player(String playerName) {
    this.playerName = playerName;
  }


  @Override
  public final boolean equals(Object o) {
    if (!(o instanceof Player player)) {
      return false;
    }

    return Objects.equals(playerName, player.playerName);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(playerName);
  }

  public String getName() {
    return playerName;
  }

  public List<Card> getCards() {
    return cards;
  }

  void addCard(Card card) {
    cards.add(card);
  }
}
