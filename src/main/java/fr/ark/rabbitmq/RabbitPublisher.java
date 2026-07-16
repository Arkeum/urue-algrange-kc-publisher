package fr.ark.rabbitmq;

import com.rabbitmq.client.Channel;
import fr.ark.models.User;
import org.keycloak.events.Event;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitPublisher {
    private final String userDeletionRoute;
    private final String userUpdateRoute;
    private final String userCreationRoute;
    private final String exchange;
    private final RabbitConnector rabbitConnector;

    private Channel channel;

    public RabbitPublisher(
            RabbitConnector rabbitConnector,
            String exchange,
            String userCreationRoute,
            String userUpdateRoute,
            String userDeletionRoute
    ) throws Exception {
        this.exchange = exchange;
        this.userCreationRoute = userCreationRoute;
        this.userUpdateRoute = userUpdateRoute;
        this.userDeletionRoute = userDeletionRoute;
        this.rabbitConnector = rabbitConnector;
        startup();
    }

    void startup() throws IOException, TimeoutException {
        //todo: rendre ce morceau resilient
        this.channel = rabbitConnector.openChannel();
        rabbitConnector.openExchange(channel);

    }

    public void publishUserUpdateMessage(Event event) throws IOException {
        String message = new User(event).toJson();
        channel.basicPublish(exchange, userUpdateRoute, null,message.getBytes());
    }

    public void publishUserCreationMessage(Event event) throws IOException {
        String message = new User(event).toJson();
        channel.basicPublish(exchange, userCreationRoute, null,message.getBytes());
    }

}
