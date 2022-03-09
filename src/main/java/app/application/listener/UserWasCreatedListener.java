package app.application.listener;

import app.config.Subscriber;
import app.domain.UserWasCreated;
import app.domain.view.UserProjection;
import app.domain.view.UserView;
import org.springframework.stereotype.Service;
import shared.domain.bus.Handler;
import shared.domain.bus.Message;
import shared.domain.bus.Response;

@Service
@Subscriber
public class UserWasCreatedListener implements Handler<UserWasCreated> {

    private final UserView userView;

    public UserWasCreatedListener(UserView userView) {
        this.userView = userView;
    }

    @Override
    public Class<? extends Message> manage() {
        return UserWasCreated.class;
    }

    @Override
    public Response handle(UserWasCreated event) {
        UserProjection user = new UserProjection(event.id().toString(), event.email().toString());
        this.userView.persist(user);

        return null;
    }
}
