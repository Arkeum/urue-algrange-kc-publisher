package fr.ark.dispatchers;

import fr.ark.exceptions.UnsuportedEventException;
import fr.ark.services.UserEventService;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.keycloak.events.Event;
import org.keycloak.events.admin.AdminEvent;

@AllArgsConstructor
public class EventDispatcher {
    private final UserEventService userEventService;

    public Try<Void> dispatch(Event event) {
        return switch (event.getType()) {
            case REGISTER -> userEventService.userRegistration(event);
            case UPDATE_PROFILE -> userEventService.userProfileUpdate(event);
            case UPDATE_EMAIL -> userEventService.userEmailUpdate(event);
            default -> Try.failure(new UnsuportedEventException("Unsupported event type: " + event.getType()));
        };
    }

    public Try<Void> dispatch(AdminEvent event, boolean b) {
        return Try.failure(new UnsupportedOperationException("Admin event not supported yet."));
    }
}
