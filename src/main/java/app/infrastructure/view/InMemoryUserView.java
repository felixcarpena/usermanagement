package app.infrastructure.view;

import app.domain.view.UserProjection;
import app.domain.view.UserView;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class InMemoryUserView implements UserView {
    private final Set<UserProjection> users;

    public InMemoryUserView(Set<UserProjection> users) {
        this.users = users;
    }

    @Override
    public Optional<UserProjection> ofId(String id) {
        return this.users.stream().filter(u -> u.id().compareTo(id) == 0).findFirst();
    }

    @Override
    public void persist(UserProjection user) {
        this.users.remove(user);
        this.users.add(user);
    }
}
