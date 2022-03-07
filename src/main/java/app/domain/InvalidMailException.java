package app.domain;

public class InvalidMailException extends Throwable {
    public InvalidMailException(String value) {
        super(String.format("Invalid email %s", value));
    }
}
