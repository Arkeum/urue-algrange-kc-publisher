package fr.ark.exceptions;

import fr.ark.urueAlgrange.ApiException;
import io.quarkus.logging.Log;

public class ApiExceptionHandler {
    public static void handle(ApiException t){
        switch (t){
            default -> defaultException((Exception) t);
        }

    }

    private static void defaultException(Exception e){
        Log.warnf("An exception occurred: %s", e.getMessage());
    }

}
