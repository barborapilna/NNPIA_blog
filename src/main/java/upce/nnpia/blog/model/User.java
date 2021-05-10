package upce.nnpia.blog.model;

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
}
