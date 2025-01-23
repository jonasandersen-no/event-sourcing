package no.jonasandersen.war.domain;

import java.util.Objects;

public class Player {

  private final String playerName;

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
}
