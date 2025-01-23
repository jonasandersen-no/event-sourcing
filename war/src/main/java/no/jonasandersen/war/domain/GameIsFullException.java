package no.jonasandersen.war.domain;

public class GameIsFullException extends RuntimeException {

  public GameIsFullException() {
    super("The game is full");
  }

}
