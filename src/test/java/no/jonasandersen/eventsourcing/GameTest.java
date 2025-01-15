package no.jonasandersen.eventsourcing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import no.jonasandersen.eventsourcing.GameEvent.GameCreated;
import no.jonasandersen.eventsourcing.GameEvent.PlayerJoined;
import org.junit.jupiter.api.Test;

class GameTest {

  @Test
  void playerIsAdded() {
    UUID gameId = UUID.randomUUID();

    var events = new GameEvent[]{
        new GameCreated(gameId),
        new PlayerJoined(gameId, "Alice")
    };

    Game game = Game.empty();
    for (var event : events) {
      game = Game.when(game, event);
    }

    assertThat(game.players().players()).containsExactly("Alice");
    assertThat(game.id()).isEqualTo(gameId);
  }
}