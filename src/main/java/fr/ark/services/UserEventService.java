package fr.ark.services;

import fr.ark.exceptions.GlobalExceptionHandler;
import fr.ark.urueAlgrange.ApiClient;
import fr.ark.urueAlgrange.apis.UserApi;
import fr.ark.urueAlgrange.dtos.*;
import io.quarkus.logging.Log;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.keycloak.events.Event;

import java.util.UUID;

@AllArgsConstructor
public class UserEventService {
    private final UserApi api;

    public Try<Void> userRegistration(Event event){
        Log.info("User registration event received");
        Log.debugf("User registration event details: %s", event.toString());

        UserDtoCreation userDto = new UserDtoCreation();
        userDto.userId(UUID.fromString(event.getUserId()))
                .firstName("")
                .lastName("");

        return Try.run(() -> api.addUser(userDto)).onFailure(GlobalExceptionHandler::handle);
    }

    public Try<Void> userEmailUpdate(Event event){
        Log.info("User email update event received");
        Log.debugf("User email update event details: %s", event.toString());

        //Not used because email is not stored on the application db

        return Try.of(() -> null);
    }

    public Try<Void> userProfileUpdate(Event event){
        Log.info("User profile update event received");
        Log.debugf("User profile update event details: %s", event.toString());

        UserDtoUpdate userDto = new UserDtoUpdate();
        userDto.userId(UUID.fromString(event.getUserId()))
                .firstName("")
                .lastName("");

        return Try.run(() -> api.updateUser(userDto)).onFailure(GlobalExceptionHandler::handle);
    }
}
