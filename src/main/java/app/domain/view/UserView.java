package app.domain.view;

import java.util.Optional;

public interface UserView {
    Optional<UserProjection> ofId(String id);

    void persist(UserProjection user);
}
