package fr.ark.exceptions;

import io.quarkus.logging.Log;

public class GlobalExceptionHandler {

    public static void handle(Throwable t){
        switch (t){
            case Error e -> throw e;
            case UnsupportedEventException exception -> unsupportedEvent(exception);
            default -> defaultException((Exception) t);
        }

    }

    private static void unsupportedEvent(UnsupportedEventException e){
        Log.warnf("Unsupported event type: %s", e.getMessage());
    }

    private static void defaultException(Exception e){
        Log.warnf("An exception occurred: %s", e.getMessage());
    }
}
