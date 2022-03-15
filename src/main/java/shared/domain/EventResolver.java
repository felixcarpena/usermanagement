package shared.domain;

import java.util.HashMap;
import java.util.Map;

public class EventResolver {
    private final Map<String, Class<? extends Event>> events;

    public EventResolver() {
        this.events = new HashMap<>();
    }

    public void register(String type, Class<? extends Event> eventClass) {
        this.events.put(type, eventClass);
    }

    public Class<? extends Event> resolve(String type) {
        return this.events.get(type);
    }
}
