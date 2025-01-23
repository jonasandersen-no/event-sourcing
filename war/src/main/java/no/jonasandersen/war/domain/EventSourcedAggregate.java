package no.jonasandersen.war.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class EventSourcedAggregate {

  private final List<GameEvent> freshEvents = new ArrayList<>();

  protected void enqueue(GameEvent event) {
    freshEvents.add(event);
    apply(event);
  }

  public void reconstruct(List<GameEvent> events) {
    for (GameEvent event : events) {
      enqueue(event);
    }
  }

  protected abstract void apply(GameEvent event);

  public Stream<GameEvent> freshEvents() {
    return freshEvents.stream();
  }

}
