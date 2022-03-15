package shared.domain;

import java.util.List;

public interface EventStore {
    AggregateHistory load(AggregateId id);

    void add(List<Event> events);
}
