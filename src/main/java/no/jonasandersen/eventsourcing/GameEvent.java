package no.jonasandersen.eventsourcing;

import java.util.UUID;

public sealed interface GameEvent {

  record GameCreated(UUID id) implements GameEvent {

  }

  record PlayerJoined(UUID gameId, String playerName) implements GameEvent {

  }

}