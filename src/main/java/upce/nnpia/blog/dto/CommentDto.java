package upce.nnpia.blog.dto;

public class CommentDto {

    private Long id;
    private String body;
    private Long postId;
    private Long userId;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public Long getPostId() { return postId; }

    public void setPostId(Long postId) { this.postId = postId; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }
}
