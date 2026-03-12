package fr.ark.services;

import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.keycloak.events.Event;

@AllArgsConstructor
public class UserEventService {

    public Try<Void> userRegistration(Event event){
        return Try.of(() -> null);
    }

    public Try<Void> userEmailUpdate(Event event){
        return Try.of(() -> null);
    }

    public Try<Void> userProfileUpdate(Event event){
        return Try.of(() -> null);
    }
}
