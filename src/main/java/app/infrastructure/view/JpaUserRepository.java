package app.infrastructure.view;

import app.domain.view.UserProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends CrudRepository<UserProjection, String> {
}
