package no.jonasandersen.event.war.domain;

import java.util.UUID;

public record PlayerJoinedEvent(UUID uuid, String playerName) implements GameEvent {

}
