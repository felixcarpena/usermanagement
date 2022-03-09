package shared.domain;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

abstract public class EventSourceAggregateRoot {
    Integer version;
    ArrayList<Event> events;

    protected EventSourceAggregateRoot() {
        this.version = 0;
        this.events = new ArrayList<>();
    }

    protected void recordThat(Event event) {
        try {
            Method method = this.getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
            this.events.add(event);
            this.version++;
        } catch (Exception e) {
            //do nothing for the moment
        }
    }

    protected void fromHistory(AggregateHistory history) {
        history.events().forEach(this::recordThat);
    }

    public List<Event> events() {
        return this.events;
    }
}
