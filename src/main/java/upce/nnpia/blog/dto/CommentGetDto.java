package upce.nnpia.blog.dto;

public class CommentGetDto {

    private Long id;
    private String body;
    private String userName;

    public CommentGetDto() {
    }

    public CommentGetDto(Long id, String body, String userName) {
        this.id = id;
        this.body = body;
        this.userName = userName;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}
}