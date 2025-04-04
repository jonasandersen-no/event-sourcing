package no.jonasandersen.event.common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class EventSourcedAggregate<EVENT> {

  private final List<EVENT> freshEvents = new ArrayList<>();

  protected void enqueue(EVENT event) {
    freshEvents.add(event);
    apply(event);
  }

  protected abstract void apply(EVENT event);

  public Stream<EVENT> freshEvents() {
    return freshEvents.stream();
  }

}
