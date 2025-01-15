package no.jonasandersen.eventsourcing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import no.jonasandersen.eventsourcing.GameEvent.GameCreated;
import no.jonasandersen.eventsourcing.GameEvent.PlayerJoined;

public sealed interface Game {

  record Players(List<String> players) {

    Players add(String player) {
      ArrayList<String> list = new ArrayList<>(players);
      list.add(player);
      return new Players(List.copyOf(list));
    }
  }

  UUID id();

  Players players();

  static Game empty() {
    return new CreatedGame(null, null);
  }


  record CreatedGame(UUID id, Players players) implements Game {

  }

  record JoinedGame(UUID id, Players players) implements Game {

  }

  static Game when(Game current, GameEvent event) {
    return switch (event) {
      case GameCreated(UUID id) -> new CreatedGame(id, new Players(Collections.emptyList()));
      case PlayerJoined playerJoined -> new JoinedGame(current.id(), current.players().add(playerJoined.playerName()));
    };
  }
}
