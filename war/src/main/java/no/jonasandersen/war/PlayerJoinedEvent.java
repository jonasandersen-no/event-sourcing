package no.jonasandersen.war;

import java.util.UUID;

public record PlayerJoinedEvent(UUID uuid, String playerName) implements GameEvent {

}
