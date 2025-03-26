package no.jonasandersen.event.war.domain;

public class PlayerNotFoundException extends RuntimeException {

  public PlayerNotFoundException(String playerName) {
    super("Player '%s' is not found".formatted(playerName));
  }
}
