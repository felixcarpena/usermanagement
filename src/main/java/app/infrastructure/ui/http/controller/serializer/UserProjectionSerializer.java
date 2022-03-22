package app.infrastructure.ui.http.controller.serializer;

import app.domain.view.UserProjection;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserProjectionSerializer extends JsonSerializer<UserProjection> {

    @Override
    public void serialize(UserProjection user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //if I start an object it fails (check how to do this properly)
        jsonGenerator.writeStringField("id", user.getId());
        jsonGenerator.writeStringField("email", user.getEmail());
    }
}
