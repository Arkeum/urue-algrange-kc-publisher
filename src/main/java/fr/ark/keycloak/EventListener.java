package fr.ark.keycloak;

import fr.ark.exceptions.GlobalExceptionHandler;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;

@AllArgsConstructor
public class EventListener implements EventListenerProvider {
    private final KeycloakSession keycloakSession;
    private final EventDispatcher eventDispatcher;

    @Override
    public void onEvent(Event event) {
        try {
            eventDispatcher.dispatch(event);
        } catch (Exception e) {
            GlobalExceptionHandler.handle(e);
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        try {
            eventDispatcher.dispatch(adminEvent, b);
        } catch (Exception e) {
            GlobalExceptionHandler.handle(e);
        }
    }

    @Override
    public void close() {

    }
}
