package app.infrastructure.persistence;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shared.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class MysqlEventStore implements EventStore {
    private final EntityManager em;
    private final EventResolver eventResolver;

    @Autowired
    public MysqlEventStore(EntityManager entityManager, EventResolver eventResolver) {
        this.em = entityManager;
        this.eventResolver = eventResolver;
    }

    @Override
    public AggregateHistory load(AggregateId id) {
        Query q = this.em.createNativeQuery(String.format("SELECT `type`, data FROM event_store WHERE aggregate_id_text = '%s' order by version asc", id.toString()));
        List<Object[]> entries = q.getResultList();
        //refactor this please and check for new ways to convert/serialize object to/from ES
        List<? extends Event> events = entries.stream().map( o -> {
             Class<? extends Event> eventClass = this.eventResolver.resolve((String) o[0]);
            JSONObject json = new JSONObject((String) o[1]);
            try {
                return eventClass.getDeclaredConstructor(JSONObject.class).newInstance(json);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                return null;
            }
        }).toList();

        return new AggregateHistory(id, events);
    }

    @Transactional
    @Override
    public void add(List<Event> events) {
        events.forEach(e -> {
            Query q = this.em.createNativeQuery("INSERT INTO event_store (aggregate_id, type, version, data) VALUES(UUID_TO_BIN(:id), :type, :version, :data)")
                    .setParameter("id", e.id().toString())
                    .setParameter("type", e.type())
                    .setParameter("version", e.version())
                    .setParameter("data", e.serialize().toString());
            q.executeUpdate();
        });
    }
}
