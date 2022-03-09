package shared.domain;

import java.lang.reflect.Method;
import java.util.ArrayList;

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
            Exception hola = e;
            //do nothing for the moment
        }
    }
}
