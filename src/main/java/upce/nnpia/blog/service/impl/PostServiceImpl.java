package upce.nnpia.blog.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import upce.nnpia.blog.dao.PostDao;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.dto.CommentGetDto;
import upce.nnpia.blog.dto.PostDto;
import upce.nnpia.blog.dto.PostGetDto;
import upce.nnpia.blog.entity.Post;
import upce.nnpia.blog.security.UserDetail;
import upce.nnpia.blog.service.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<PostGetDto> findAll() {
        List<PostGetDto> postsGetDto = new ArrayList<>();
        List<Post> posts = postDao.findAll();

        for (var post : posts) {
            postsGetDto.add(new PostGetDto(post.getId(), post.getTitle(), post.getBody(), post.getUser().getUsername()));
        }

        return postsGetDto;
    }

    @Override
    public void delete(Long id) {
        postDao.deleteById(id);
    }

    @Override
    public PostGetDto findOne(String title) {
        PostGetDto postGetDto = new PostGetDto();
        Post post = postDao.findByTitle(title);

        postGetDto.setId(post.getId());
        postGetDto.setBody(post.getBody());
        postGetDto.setTitle(post.getTitle());
        postGetDto.setUserName(post.getUser().getUsername());

        return postGetDto;
    }

    @Override
    public PostGetDto findById(Long id) {
        PostGetDto postGetDto = new PostGetDto();
        Optional<Post> post = postDao.findById(id);

        postGetDto.setId(post.orElseThrow().getId());
        postGetDto.setBody(post.get().getBody());
        postGetDto.setTitle(post.get().getTitle());
        postGetDto.setUserName(post.get().getUser().getUsername());

        return postGetDto;
    }

    @Override
    public void update(PostDto post) {
        Post newPost = postDao.findById(post.getId()).orElseThrow();
        BeanUtils.copyProperties(post, newPost);
        postDao.save(newPost);
    }
}
