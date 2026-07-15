package fr.ark.keycloak;

import fr.ark.rabbitmq.RabbitConnector;
import fr.ark.rabbitmq.RabbitConnectorImpl;
import fr.ark.rabbitmq.publishers.RabbitUserPublisher;
import fr.ark.utilities.ConfigLoader;
import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class EventListenerFactory implements EventListenerProviderFactory {
    private ConfigLoader config;
    private EventDispatcher eventDispatcher;
    private RabbitUserPublisher rabbitUserPublisher;

    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        if(this.eventDispatcher == null) throw new IllegalStateException("EventDispatcher not initialized");
        return new EventListener(keycloakSession,this.eventDispatcher);
    }

    @Override
    public void init(Config.Scope scope) {
        this.config = new ConfigLoader("config.properties");
        try {
            initQueueConnection();
            eventDispatcher = new EventDispatcher(rabbitUserPublisher);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize provider components", e);
        }
    }

    public void initQueueConnection() throws Exception{
        RabbitConnector rabbitConnector = new RabbitConnectorImpl(
                config.get("rabbitmq.username"),
                config.get("rabbitmq.password"),
                config.get("rabbitmq.virtual.host"),
                config.get("rabbitmq.host"),
                Integer.parseInt(config.get("rabbitmq.port")),
                config.get("rabbitmq.exchange")
        );

        rabbitUserPublisher = new RabbitUserPublisher(
                rabbitConnector,
                config.get("rabbitmq.exchange"),
                config.get("rabbitmq.routing.user-created"),
                config.get("rabbitmq.routing.user-updated"),
                config.get("rabbitmq.routing.user-deleted")
        );
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return "urueKeycloakPublisher";
    }
}
