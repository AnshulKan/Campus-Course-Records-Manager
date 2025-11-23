package src;

import java.io.Serializable;

/**
 * Abstract base class representing a generic user.
 */
public abstract class User implements Serializable, Displayable {
    protected String id;
    protected String name;
    protected String password;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public boolean validateLogin(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    
    // Abstract method forcing subclasses to define their specific menu
    public abstract void showMenu();
}