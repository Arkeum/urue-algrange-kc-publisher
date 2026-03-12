package fr.ark.factories;

import fr.ark.dispatchers.EventDispatcher;
import fr.ark.providers.EventListener;
import lombok.AllArgsConstructor;
import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

@AllArgsConstructor
public class EventListenerFactory implements EventListenerProviderFactory {
    private final EventDispatcher eventDispatcher;

    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        return new EventListener(keycloakSession,eventDispatcher);
    }

    @Override
    public void init(Config.Scope scope) {

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
