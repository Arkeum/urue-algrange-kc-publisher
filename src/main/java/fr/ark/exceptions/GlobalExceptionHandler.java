package fr.ark.exceptions;

import io.quarkus.logging.Log;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class GlobalExceptionHandler {

    public static void handle(Throwable t){
        switch (t){
            case Error e -> throw e;
            case UnsupportedEventException exception -> unsupportedEvent(exception);
            case ConfigNotFoundException exception -> configNotFoundException(exception);
            case TimeoutException exception -> timeoutException(exception);
            case IOException exception -> ioException(exception);
            default -> defaultException((Exception) t);
        }

    }

    private static void unsupportedEvent(UnsupportedEventException e){
        Log.infof("Unsupported event type: %s", e.getMessage());
    }

    private static void defaultException(Exception e){
        Log.errorf("An exception occurred: %s", e.getMessage());
    }

    private static void configNotFoundException(ConfigNotFoundException e){
        Log.errorf("Configuration retrieval failed: %s", e.getMessage());
    }

    private static void timeoutException(TimeoutException e){
        Log.errorf("Timeout occurred: %s", e.getMessage());
    }

    private static void ioException(IOException e){
        Log.errorf("I/O exception occurred: %s", e.getMessage());
    }
}
