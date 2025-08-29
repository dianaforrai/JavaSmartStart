package com.gl;

import com.gl.dao.UserDao;
import com.gl.model.User;
import com.gl.model.Post;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserPostMappingTest {

    private static UserDao userDao;

    @BeforeAll
    static void setup() {
        userDao = new UserDao();
    }

    @Test
    void testUserWithPosts() {
        User user = new User("bob", "bob@example.com");
        Post post1 = new Post("Hello", "Hello World!");
        Post post2 = new Post("Hibernate", "Learning Hibernate.");

        user.addPost(post1);
        user.addPost(post2);

        userDao.save(user);

        assertTrue(user.getPosts().size() == 2);
    }

    @AfterAll
    static void teardown() {
        userDao.close();
    }
}
