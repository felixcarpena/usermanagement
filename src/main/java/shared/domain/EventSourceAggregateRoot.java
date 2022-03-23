package shared.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

abstract public class EventSourceAggregateRoot {
    private Long version;
    private final ArrayList<Event> events;

    protected EventSourceAggregateRoot() {
        this.version = 0L;
        this.events = new ArrayList<>();
    }

    protected void recordThat(Event event) {
        try {
            this.apply(event);
            this.events.add(event);
        } catch (Exception e) {
            //do nothing for the moment
        }
    }

    protected void apply(Event event) {
        try {
            this.version = event.version();
            Method method = this.getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            //do nothing for the moment
        }
    }

    protected void fromHistory(AggregateHistory history) {
        history.events().forEach(this::apply);
    }

    public List<Event> events() {
        return this.events;
    }

    protected long nextVersion() {
        return ++this.version;
    }
}
