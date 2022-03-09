package app.domain.view;

public class UserProjection {
    private final String id;
    private final String email;

    public UserProjection(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String id() {
        return id;
    }

    public String email() {
        return email;
    }
}
