package app.application.listener;

import app.config.Subscriber;
import app.domain.UserEmailWasUpdated;
import app.domain.view.UserProjection;
import app.domain.view.UserView;
import org.springframework.stereotype.Service;
import shared.domain.bus.Handler;
import shared.domain.bus.Message;
import shared.domain.bus.Response;

@Service
@Subscriber
public class UserEmailWasUpdatedListener implements Handler<UserEmailWasUpdated> {

    private final UserView userView;

    public UserEmailWasUpdatedListener(UserView userView) {
        this.userView = userView;
    }

    @Override
    public Class<? extends Message> manage() {
        return UserEmailWasUpdated.class;
    }

    @Override
    public Response handle(UserEmailWasUpdated event) {
        UserProjection user = this.userView.ofId(event.id().toString()).orElse(new UserProjection(event.id().toString(), event.email().toString()));
        user.setEmail(event.email().toString());
        this.userView.persist(user);

        return null;
    }
}
