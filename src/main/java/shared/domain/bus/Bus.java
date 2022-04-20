package shared.domain.bus;

import shared.domain.Event;

import java.util.Optional;

public interface Bus {
    void subscribe(Handler<Message> handler);
    void dispatch(Event event);
    void dispatch(Command command);
    Optional<Response> dispatch(Query query);
}
