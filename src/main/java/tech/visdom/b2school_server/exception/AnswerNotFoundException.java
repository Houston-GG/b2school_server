package tech.visdom.b2school_server.exception;

public class AnswerNotFoundException extends RuntimeException{
    public AnswerNotFoundException(String message) {
        super(message);
    }
}
