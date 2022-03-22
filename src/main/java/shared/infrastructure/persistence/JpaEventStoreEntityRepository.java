package shared.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaEventStoreEntityRepository extends CrudRepository<JpaEventStoreEntity, Long> {
    List<JpaEventStoreEntity> findByAggregateIdOrderByVersionDesc(String id);
}
