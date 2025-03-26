package no.jonasandersen.event.war.domain;

import java.util.List;

public record DeckCreatedEvent(List<Card> cards) implements GameEvent {

}
