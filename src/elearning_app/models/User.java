package elearning_app.models;
import java.util.UUID;
public abstract class User {
    private final String id;
    private String name;
    private String email;
    private String password;

    protected User(String name, String email, String password) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //id nu are setter pt ca e imutabil
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[a-z0-9+_.-]+@[a-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email!");
        }
        this.email = email.toLowerCase();

    }
    public String getPassword() {
        return password;
    }

}