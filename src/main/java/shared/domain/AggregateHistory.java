package shared.domain;

import java.util.List;

public final class AggregateHistory {
    private final AggregateId id;
    private final List<? extends Event> events;

    public AggregateHistory(AggregateId id, List<? extends Event> events) {
        this.id = id;
        this.events = events;
    }

    public AggregateId id() {
        return id;
    }

    public List<? extends Event> events() {
        return events;
    }

    public boolean isEmpty() {
        return this.events.isEmpty();
    }
}
