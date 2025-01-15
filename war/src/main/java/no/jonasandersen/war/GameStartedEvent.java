package no.jonasandersen.war;

import java.util.UUID;

public record GameStartedEvent(UUID id) implements GameEvent {

}
