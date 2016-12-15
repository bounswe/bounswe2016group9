package boun.cmpe451.group9;

import boun.cmpe451.group9.Models.DB.Topic;
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

    //TODO Fill this area with additional unit tests
}
