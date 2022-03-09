package app.application.query;

import app.domain.view.UserProjection;
import shared.domain.bus.Response;

final public class UserOfIdResponse implements Response {
    public final UserProjection user;

    public UserOfIdResponse(UserProjection user) {
        this.user = user;
    }

    public UserProjection user() {
        return user;
    }
}
