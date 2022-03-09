package shared.domain;

import java.util.List;

final public class AggregateHistory {
    private final AggregateId id;
    private final List<Event> events;

    public AggregateHistory(AggregateId id, List<Event> events) {
        this.id = id;
        this.events = events;
    }

    public AggregateId id() {
        return id;
    }

    public List<Event> events() {
        return events;
    }
}
