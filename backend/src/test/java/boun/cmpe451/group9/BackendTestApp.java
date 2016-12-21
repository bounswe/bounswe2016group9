package boun.cmpe451.group9;

import boun.cmpe451.group9.Models.DB.*;
import boun.cmpe451.group9.Service.*;

import boun.cmpe451.group9.Service.Comment.CommentService;
import boun.cmpe451.group9.Service.Post.PostService;
import boun.cmpe451.group9.Service.Topic.TopicService;
import boun.cmpe451.group9.Service.User.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BackendTestApp {

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    protected PostService postService;

    @Autowired
    private CommentService commentService;


    @Test
    public void deneme(){
        int number = 2;
        assertEquals(number, 2);
    }

    @Test
    public void testSaveAndGetTopic(){
        Topic t = new Topic();
        t.setName("mert");
        t.setUser(userService.getById(1));
        topicService.save(t);

        assertEquals(topicService.getById(t.getEntityId()), t);
    }
    @Test
    public void testSaveAndGetPostUnderTopic(){
        Topic t = new Topic();
        t.setName("test topic");
        t.setUser(userService.getById(1));
        topicService.save(t);

        Post post= new Post();
        post.setContent("test post");
        post.setCreatedUser(userService.getById(1));
        post.setTopic(t);
        postService.save(post);

        assertEquals(topicService.getById(t.getEntityId()), t);
        assertEquals(postService.getById(post.getEntityId()),post);
    }

    @Test
    public void testSaveComment(){
        Topic t = new Topic();
        t.setName("test topic for commenting");
        t.setUser(userService.getById(1));
        topicService.save(t);

        Post post= new Post();
        post.setContent("test post for commenting");
        post.setCreatedUser(userService.getById(1));
        post.setTopic(t);
        postService.save(post);

        Comment comment= new Comment();
        comment.setContent("hello world!");
        comment.setCreatedUser(userService.getById(1));
        comment.setPostOfComment(post);
        commentService.save(comment);

        assertEquals(topicService.getById(t.getEntityId()), t);
        assertEquals(postService.getById(post.getEntityId()),post);
        assertEquals(commentService.getById(comment.getEntityId()),comment);

    }
}
