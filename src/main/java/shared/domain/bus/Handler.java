package shared.domain.bus;

public interface Handler<T extends Message> {
    Response handle(T message);

    Class<? extends Message> manage();
}
