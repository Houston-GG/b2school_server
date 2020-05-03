package tech.visdom.b2school_server.exception;

public class ExercisesNotFoundException extends RuntimeException{
    public ExercisesNotFoundException(String message) {
        super(message);
    }
}
