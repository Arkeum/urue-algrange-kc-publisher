package fr.ark.listeners;

import fr.ark.dispatchers.EventDispatcher;
import fr.ark.exceptions.GlobalExceptionHandler;
import fr.ark.urueAlgrange.ApiClient;
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
        eventDispatcher.dispatch(event)
                .onFailure(GlobalExceptionHandler::handle);
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        eventDispatcher.dispatch(adminEvent, b)
                .onFailure(GlobalExceptionHandler::handle);
    }

    @Override
    public void close() {

    }
}
