package no.jonasandersen.war.domain;

public sealed interface GameEvent permits DeckCreatedEvent, GameStartedEvent, PlayerJoinedEvent {

}
