package fr.ark.dispatchers;

import fr.ark.services.UserEventService;
import lombok.AllArgsConstructor;
import org.keycloak.events.Event;
import org.keycloak.events.admin.AdminEvent;

@AllArgsConstructor
public class EventDispatcher {
    private final UserEventService userEventService;

    public void dispatch(Event event) {
        switch(event.getType()){
            case REGISTER:
                break;
            case UPDATE_PROFILE:
                break;
            case UPDATE_EMAIL:
                break;
            default:
                break;
        }
    }

    public void dispatch(AdminEvent event, boolean b) {

    }
}
