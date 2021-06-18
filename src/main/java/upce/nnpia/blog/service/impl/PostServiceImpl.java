package upce.nnpia.blog.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upce.nnpia.blog.dao.PostDao;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.dto.PostDto;
import upce.nnpia.blog.entity.Post;
import upce.nnpia.blog.service.PostService;
import java.util.List;
import java.util.Optional;

@Service(value = "postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void save(PostDto post) {
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());
        newPost.setUser(userDao.findById(post.getUserId()).get());
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
    public Post findOne(String title) {
        return postDao.findByTitle(title);
    }

    @Override
    public Optional<Post> findById(long id) {
        return postDao.findById(id);
    }

    @Override
    public void update(PostDto post) {
        Post newPost = postDao.findById(post.getId()).get();
        BeanUtils.copyProperties(post, newPost);
        postDao.save(newPost);
    }
}
