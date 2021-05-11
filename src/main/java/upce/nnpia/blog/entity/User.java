package upce.nnpia.blog.entity;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "first_name", columnDefinition = "TEXT", nullable = false)
    private String firstName;

    @Column(name = "last_name", columnDefinition = "TEXT", nullable = false)
    private String lastName;

    @Column(name = "user_name", columnDefinition = "TEXT", nullable = false, unique=true)
    private String username;

    @Column(name = "password", columnDefinition = "TEXT", nullable = false)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
