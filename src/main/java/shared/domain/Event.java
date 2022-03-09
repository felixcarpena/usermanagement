package shared.domain;

import org.json.JSONException;
import org.json.JSONObject;
import shared.domain.bus.Message;

public interface Event extends Message {
    AggregateId id();

    JSONObject serialize() throws JSONException;

    Event deserialize(JSONObject value) throws JSONException;
}
