package shared.domain;

import java.util.List;

public interface EventStore {
    public AggregateHistory load(AggregateId id);

    public void add(List<Event> events);
}
