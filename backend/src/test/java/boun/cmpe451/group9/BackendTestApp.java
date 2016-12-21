package boun.cmpe451.group9;

import boun.cmpe451.group9.Models.DB.*;
import boun.cmpe451.group9.Service.*;

import boun.cmpe451.group9.Service.Comment.CommentService;
import boun.cmpe451.group9.Service.FollowRel.FollowRelService;
import boun.cmpe451.group9.Service.FollowTopic.FollowTopicService;
import boun.cmpe451.group9.Service.Post.PostService;
import boun.cmpe451.group9.Service.Relation.RelationService;
import boun.cmpe451.group9.Service.Topic.TopicService;
import boun.cmpe451.group9.Service.User.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

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
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RelationService relationService;

    @Autowired
    private FollowTopicService followTopicService;

    @Autowired
    private FollowRelService followRelService;

    @Test
    public void deneme(){
        int number = 2;
        assertEquals(number, 2);
    }

    @Test
    public void testRelation(){
        Topic t = new Topic();
        t.setName("mert");
        t.setUser(userService.getById(1));
        topicService.save(t);

        Topic t1 = new Topic();
        t1.setName("mamet");
        t1.setUser(userService.getById(1));
        topicService.save(t1);

        Relation r= new Relation();
        RelationType relationType=new RelationType();
        relationType.setType("lol");
        r.setCreatedUser(userService.getById(1));
        r.setFromTopic(t);
        r.setToTopic(t1);
        r.setRelationType(relationType);

        relationService.save(r);
        assertEquals(topicService.getById(t.getEntityId()), t);
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
        System.out.println(post.getEntityId());

        assertEquals(topicService.getById(t.getEntityId()), t);
//        assertEquals(postService.getById(post.getEntityId()),post);
    }

    @Test
    public void testFollowTopic(){
        Topic topic = new Topic();
        topic.setName("test topic for following topic");
        topic.setUser(userService.getById(1));
        topicService.save(topic);
        FollowTopic followTopic = new FollowTopic();
        followTopic.setFollower(userService.getById(1));
        followTopic.setTopic(topic);
        followTopicService.save(followTopic);

        assertEquals(followTopicService.getById(followTopic.getEntityId()),followTopic);

    }

    @Test
    public void testFollowRel(){
        FollowRel followRel = new FollowRel();
        followRel.setFollower(userService.getById(1));
        followRel.setFollowing(userService.getById(2));
        followRelService.save(followRel);
        assertEquals(followRelService.getById(followRel.getEntityId()),followRel);

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
        //postService.save(post);

        Comment comment= new Comment();
        comment.setContent("hello world!");
        comment.setCreatedUser(userService.getById(1));
        comment.setPostOfComment(post);
        commentService.save(comment);

        assertEquals(topicService.getById(t.getEntityId()), t);
        //assertEquals(postService.getById(post.getEntityId()),post);
        assertEquals(commentService.getById(comment.getEntityId()),comment);

    }
}
