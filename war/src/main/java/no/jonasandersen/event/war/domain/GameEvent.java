package no.jonasandersen.event.war.domain;

public sealed interface GameEvent permits DealCardEvent, DeckCreatedEvent, GameCreatedEvent, PlayerJoinedEvent {

}
