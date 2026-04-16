package fr.ark.exceptions;

import fr.ark.urueAlgrange.ApiException;
import io.quarkus.logging.Log;

public class GlobalExceptionHandler {

    public static void handle(Throwable t){
        switch (t){
            case Error e -> throw e;
            case UnsuportedEventException exception -> unsuportedEvent(exception);
            case ApiException exception -> apiException(exception);
            default -> defaultException((Exception) t);
        }

    }

    private static void unsuportedEvent(UnsuportedEventException e){
        Log.warnf("Unsuported event type: %s", e.getMessage());
    }

    private static void apiException(ApiException e){
        Log.warnf("API exception occurred: %s", e.getMessage());
        ApiExceptionHandler.handle(e);
    }

    private static void defaultException(Exception e){
        Log.warnf("An exception occurred: %s", e.getMessage());
    }
}
