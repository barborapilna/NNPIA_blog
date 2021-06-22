package upce.nnpia.blog.dataFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upce.nnpia.blog.dao.PostDao;
import upce.nnpia.blog.entity.Post;

@Component
public class PostTestDataFactory {

    @Autowired
    private PostDao postDao;

    public Post saveNewPost1() {
        Post post = new Post();
        post.setTitle("This post has just title");
        post.setBody("This is a post body. This is a post body. This is a post body.");
        postDao.save(post);
        return post;
    }

    public Post saveNewPost2() {
        Post post = new Post();
        post.setTitle("This is a post title");
        post.setBody("Another post body. Another post body. Another post body.");
        postDao.save(post);
        return post;
    }
}
