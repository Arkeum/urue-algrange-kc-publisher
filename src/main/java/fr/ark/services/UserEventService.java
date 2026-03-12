package fr.ark.services;

import io.quarkus.logging.Log;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.keycloak.events.Event;

@AllArgsConstructor
public class UserEventService {

    public Try<Void> userRegistration(Event event){
        Log.info("User registration event received");
        Log.debugf("User registration event details: %s", event.toString());
        return Try.of(() -> null);
    }

    public Try<Void> userEmailUpdate(Event event){
        Log.info("User email update event received");
        Log.debugf("User email update event details: %s", event.toString());
        return Try.of(() -> null);
    }

    public Try<Void> userProfileUpdate(Event event){
        Log.info("User profile update event received");
        Log.debugf("User profile update event details: %s", event.toString());
        return Try.of(() -> null);
    }
}
