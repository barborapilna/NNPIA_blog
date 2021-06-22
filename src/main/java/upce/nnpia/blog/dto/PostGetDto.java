package upce.nnpia.blog.dto;

public class PostGetDto {

    private Long id;
    private String title;
    private String body;
    private String userName;

    public PostGetDto() {
    }

    public PostGetDto(Long id, String title, String body, String userName) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userName = userName;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getBody() {return body;}

    public void setBody(String body) {this.body = body;}

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}
}
