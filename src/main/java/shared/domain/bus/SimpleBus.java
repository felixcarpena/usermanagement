package shared.domain.bus;

import org.springframework.stereotype.Service;
import shared.domain.Event;

import java.util.*;

@Service
public class SimpleBus implements Bus {
    //I don't think using class is a good idea as a key of hashmap but I'll keep it for the moment
    private final Map<Class<? extends Message>, List<Handler<Message>>> handlers;

    public SimpleBus() {
        this.handlers = new HashMap<>();
    }

    public void subscribe(Handler<Message> handler) {
        this.handlers.computeIfAbsent(handler.manage(), k -> new ArrayList<>());
        this.handlers.get(handler.manage()).add(handler);
    }

    @Override
    public void dispatch(Event event) {
        //multiple handlers
        this.handlers.getOrDefault(event.getClass(), new ArrayList<>()).forEach(handler -> handler.handle(event));
    }

    @Override
    public void dispatch(Command command) {
        //one handler
        this.handlers.getOrDefault(command.getClass(), new ArrayList<>()).stream().findFirst().ifPresent(h -> h.handle(command));
    }

    @Override
    public Optional<Response> dispatch(Query query) {
        //one handler with return optional
        return this.handlers.getOrDefault(query.getClass(), new ArrayList<>()).stream().findFirst().map(h -> h.handle(query));
    }
}
