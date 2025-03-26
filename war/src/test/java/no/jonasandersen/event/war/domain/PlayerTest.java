package no.jonasandersen.event.war.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PlayerTest {

  @Test
  void playerNameIsSet() {
    Player player = new Player("Player 1");

    assertThat(player.getName())
        .isEqualTo("Player 1");
  }
}