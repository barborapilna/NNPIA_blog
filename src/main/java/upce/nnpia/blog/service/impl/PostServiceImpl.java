package upce.nnpia.blog.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import upce.nnpia.blog.dao.PostDao;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.dto.PostDto;
import upce.nnpia.blog.dto.PostGetDto;
import upce.nnpia.blog.entity.Post;
import upce.nnpia.blog.security.UserDetail;
import upce.nnpia.blog.service.PostService;
import java.util.List;

@Service(value = "postService")
public class PostServiceImpl implements PostService {

    private final PostDao postDao;
    private final UserDao userDao;

    public PostServiceImpl(PostDao postDao, UserDao userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @Override
    public void save(UserDetail user, PostDto post) {
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());
        newPost.setUser(userDao.findByUsername(user.getUsername()));
        postDao.saveAndFlush(newPost);
    }

    @Override
    public List<Post> findAll() {
        return postDao.findAll();
    }

    @Override
    public void delete(long id) {
        postDao.findById(id).ifPresent(p -> {
            postDao.delete(p);
        });
    }

    @Override
    public PostGetDto findOne(String title) {
        var postGetDto = new PostGetDto();
        var post = postDao.findByTitle(title);

        postGetDto.setId(post.getId());
        postGetDto.setBody(post.getBody());
        postGetDto.setTitle(post.getTitle());
        postGetDto.setUserName(post.getUser().getUsername());

        return postGetDto;
    }

    @Override
    public PostGetDto findById(long id) {
        var postGetDto = new PostGetDto();
        var post = postDao.findById(id);

        postGetDto.setId(post.get().getId());
        postGetDto.setBody(post.get().getBody());
        postGetDto.setTitle(post.get().getTitle());
        postGetDto.setUserName(post.get().getUser().getUsername());

        return postGetDto;
    }

    @Override
    public void update(PostDto post) {
        Post newPost = postDao.findById(post.getId()).get();
        BeanUtils.copyProperties(post, newPost);
        postDao.save(newPost);
    }
}
