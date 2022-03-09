package app.infrastructure.persistence.inmemory;

import org.springframework.stereotype.Service;
import shared.domain.AggregateHistory;
import shared.domain.AggregateId;
import shared.domain.Event;
import shared.domain.EventStore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InMemoryEventStore implements EventStore {
    private final List<Event> events;

    public InMemoryEventStore() {
        this.events = new ArrayList<>();
    }

    @Override
    public AggregateHistory load(AggregateId id) {
        List<Event> events = this.events.stream().filter(e -> e.id().equals(id)).collect(Collectors.toList());

        return new AggregateHistory(id, events);
    }

    @Override
    public void add(List<Event> events) {
        this.events.addAll(events);
    }
}
