package app.domain;

import org.json.JSONException;
import org.json.JSONObject;
import shared.domain.Event;

import java.util.UUID;

public class UserWasCreated implements Event {
    private final UserId id;
    private final Email email;
    private final long version;

    public UserWasCreated(UserId id, Email email, long version) {
        this.id = id;
        this.email = email;
        this.version = version;
    }

    public UserWasCreated(JSONObject rawEvent) {
        try {
            UserId aggregate_id = new UserId(UUID.fromString(rawEvent.getString("aggregate_id")));
            Email email = new Email(rawEvent.getString("email"));
            long version = rawEvent.getLong("version");
            this.id = aggregate_id;
            this.email = email;
            this.version = version;
        } catch (InvalidMailException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public String type() {
        return "user.was_created";
    }

    public UserId id() {
        return id;
    }

    public Email email() {
        return email;
    }

    public long version() {
        return version;
    }

    @Override
    public JSONObject serialize() throws JSONException {
        JSONObject event = new JSONObject();
        event.put("aggregate_id", this.id.toString());
        event.put("email", this.email.toString());
        event.put("version", this.version);

        return event;
    }
}
