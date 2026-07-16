package fr.ark.keycloak;

import fr.ark.exceptions.UnsupportedEventException;
import fr.ark.rabbitmq.RabbitPublisher;
import lombok.AllArgsConstructor;
import org.keycloak.events.Event;
import org.keycloak.events.admin.AdminEvent;

@AllArgsConstructor
public class EventDispatcher {
    private final RabbitPublisher rabbitPublisher;

    public void dispatch(Event event) throws Exception{
        switch (event.getType()) {
            case REGISTER -> rabbitPublisher.publishUserCreationMessage(event);
            case UPDATE_PROFILE -> rabbitPublisher.publishUserUpdateMessage(event);
            default -> throw new UnsupportedEventException("Unsupported event type: " + event.getType());
        };
    }

    public void dispatch(AdminEvent event, boolean b) throws Exception{
        throw new UnsupportedOperationException("Admin event not supported yet.");
    }
}
