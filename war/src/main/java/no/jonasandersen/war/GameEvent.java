package no.jonasandersen.war;

public sealed interface GameEvent permits DeckCreatedEvent, GameStartedEvent {

}
