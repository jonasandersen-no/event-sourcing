package no.jonasandersen.war.domain;

import java.util.UUID;

public record GameStartedEvent(UUID id) implements GameEvent {

}
