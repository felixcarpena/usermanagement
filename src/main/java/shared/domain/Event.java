package shared.domain;

import org.json.JSONException;
import org.json.JSONObject;

public interface Event {
    JSONObject serialize() throws JSONException;

    Event deserialize(JSONObject value) throws JSONException;
}
