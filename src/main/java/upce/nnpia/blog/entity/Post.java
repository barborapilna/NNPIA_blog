package upce.nnpia.blog.entity;

import org.jetbrains.annotations.NotNull;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String body;

    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE)
    private Set<Comment> comments;

    @ManyToOne
    @NotNull
    private User user;

    public Post() {}

    public Post(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<Comment> getComments() { return comments; }

    public void setComments(Set<Comment> comments) { this.comments = comments; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
