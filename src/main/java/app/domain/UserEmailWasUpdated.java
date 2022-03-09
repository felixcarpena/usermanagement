package app.domain;

import org.json.JSONException;
import org.json.JSONObject;
import shared.domain.Event;

import java.util.UUID;

public class UserEmailWasUpdated implements Event {
    private final UserId id;
    private final Email email;

    public UserEmailWasUpdated(UserId id, Email email) {
        this.id = id;
        this.email = email;
    }

    public UserId id() {
        return id;
    }

    public Email email() {
        return email;
    }

    @Override
    public JSONObject serialize() throws JSONException {
        JSONObject event = new JSONObject();
        event.put("aggregate_id", this.id.toString());
        event.put("email", this.email.toString());

        return event;
    }

    @Override
    public UserEmailWasUpdated deserialize(JSONObject event) throws JSONException {
        try {
            Email email = new Email(event.getString("email"));
            UserId aggregate_id = new UserId(UUID.fromString(event.getString("aggregate_id")));
            return new UserEmailWasUpdated(aggregate_id, email);
        } catch (InvalidMailException e) {
            throw new JSONException(e.getMessage());
        }
    }
}
