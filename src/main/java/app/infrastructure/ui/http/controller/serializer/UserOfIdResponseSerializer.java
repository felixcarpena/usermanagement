package app.infrastructure.ui.http.controller.serializer;

import app.application.query.UserOfIdResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserOfIdResponseSerializer extends JsonSerializer<UserOfIdResponse> {

    @Override
    public void serialize(UserOfIdResponse userOfIdResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(userOfIdResponse.user());
        jsonGenerator.writeEndObject();
    }
}
