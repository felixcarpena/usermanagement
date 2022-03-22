package shared.infrastructure.persistence;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shared.domain.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@Primary
public class JpaEventStore implements EventStore {
    private JpaEventStoreEntityRepository repository;
    private final EventResolver eventResolver;

    @Autowired
    public JpaEventStore(JpaEventStoreEntityRepository repository, EventResolver eventResolver) {
        this.repository = repository;
        this.eventResolver = eventResolver;
    }

    @Override
    public AggregateHistory load(AggregateId id) {
        List<JpaEventStoreEntity> entries = this.repository.findByAggregateIdOrderByVersionDesc(id.toString());
        //refactor this please and check for new ways to convert/serialize object to/from ES
        List<? extends Event> events = entries.stream().map(e -> {
            Class<? extends Event> eventClass = this.eventResolver.resolve(e.getType());
            try {
                return eventClass.getDeclaredConstructor(JSONObject.class).newInstance(new JSONObject(e.getData()));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                return null;
            }
        }).toList();

        return new AggregateHistory(id, events);
    }

    @Transactional
    @Override
    public void add(List<Event> events) {
        events.forEach(e -> this.repository.save(new JpaEventStoreEntity(1L, e.id().toString(), e.type(), e.version(), e.serialize().toString())));
    }
}
