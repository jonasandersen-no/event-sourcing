package no.jonasandersen.war;

import java.util.List;

public record DeckCreatedEvent(List<Card> cards) implements GameEvent {

}
