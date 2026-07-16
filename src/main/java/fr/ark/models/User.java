package fr.ark.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Builder;
import org.keycloak.events.Event;

import java.util.Map;
import java.util.UUID;

public class User {
    @JsonIgnore
    private static final ObjectWriter objectWriter = new ObjectMapper()
            .writer()
            .withDefaultPrettyPrinter();

    public String userId;

    @JsonAnyGetter
    public Map<String, String> attributes;

    public User(Event event) {
        this.userId = event.getUserId();
        this.attributes = event.getDetails();
    }

    public String toJson() throws JsonProcessingException {
        return objectWriter.writeValueAsString(this);
    }
}
