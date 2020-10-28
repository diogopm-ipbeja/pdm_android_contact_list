package pt.ipbeja.pdm.contacts;

/**
 * Contact Data class
 */
public class Contact {

    private final String name;
    private final String username;

    public Contact(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
