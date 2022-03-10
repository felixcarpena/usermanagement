package app.application.query;

import app.config.Subscriber;
import app.domain.view.UserView;
import org.springframework.stereotype.Service;
import shared.domain.bus.Handler;
import shared.domain.bus.Message;

@Service
@Subscriber
public class UserOfIdQueryHandler implements Handler<UserOfId> {

    private final UserView userView;

    public UserOfIdQueryHandler(UserView userView) {
        this.userView = userView;
    }

    @Override
    public Class<? extends Message> manage() {
        return UserOfId.class;
    }

    @Override
    public UserOfIdResponse handle(UserOfId query) {
        return this.userView.ofId(query.id()).map(UserOfIdResponse::new).orElse(null);
    }
}
