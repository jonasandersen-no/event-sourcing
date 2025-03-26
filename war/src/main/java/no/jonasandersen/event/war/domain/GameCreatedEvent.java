package no.jonasandersen.event.war.domain;

import java.util.UUID;

public record GameCreatedEvent(UUID id) implements GameEvent {

}
