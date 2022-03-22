package app.infrastructure.view;

import app.domain.view.UserProjection;
import app.domain.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public class JpaUserView implements UserView {
    private final JpaUserRepository repository;

    @Autowired
    public JpaUserView(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UserProjection> ofId(String id) {
        return this.repository.findById(id);
    }

    @Override
    public void persist(UserProjection user) {
        this.repository.save(user);
    }
}
