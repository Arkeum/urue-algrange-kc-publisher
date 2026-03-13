package fr.ark.factories;

import fr.ark.dispatchers.EventDispatcher;
import fr.ark.listeners.EventListener;
import fr.ark.services.UserEventService;
import lombok.AllArgsConstructor;
import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class EventListenerFactory implements EventListenerProviderFactory {
    private EventDispatcher eventDispatcher;

    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        if(this.eventDispatcher == null) throw new IllegalStateException("EventDispatcher not initialized");
        return new EventListener(keycloakSession,this.eventDispatcher);
    }

    @Override
    public void init(Config.Scope scope) {
        UserEventService userEventService = new UserEventService();
        this.eventDispatcher = new EventDispatcher(userEventService);
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return "urueEventsListener";
    }
}
