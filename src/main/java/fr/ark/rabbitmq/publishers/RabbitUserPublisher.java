package fr.ark.rabbitmq.publishers;

import com.rabbitmq.client.Channel;
import fr.ark.rabbitmq.RabbitConnector;
import org.keycloak.events.Event;

public class RabbitUserPublisher {
    private final String userDeletionRoute;
    private final String userUpdateRoute;
    private final String userCreationRoute;
    private final String exchange;
    private final RabbitConnector rabbitConnector;

    private Channel channel;

    public RabbitUserPublisher(
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

    void startup() throws Exception {
        //todo: rendre ce morceau resilient
        this.channel = rabbitConnector.openChannel();
        rabbitConnector.openExchange(channel);

    }

    public void publishUserCreationMessage(Event event) throws Exception {
        channel.basicPublish(exchange, userCreationRoute, null, "".getBytes());
    }

    public void publishUserUpdateMessage(Event event) throws Exception {
        channel.basicPublish(exchange, userUpdateRoute, null,"".getBytes());
    }

    public void publishUserDeletionMessage(Event event) throws Exception {
        channel.basicPublish(exchange, userDeletionRoute, null, "".getBytes());
    }

}
