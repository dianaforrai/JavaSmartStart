package com.gl;

import com.gl.dao.UserDao;
import com.gl.model.Post;
import com.gl.model.User;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        User user = new User("john_doe", "john@example.com");
        Post post1 = new Post("Hibernate Basics", "Content of Hibernate Basics");
        Post post2 = new Post("JPA Relationships", "Content of JPA Relationships");

        user.addPost(post1);
        user.addPost(post2);

        userDao.insert(user);

        System.out.println("Inserted User: " + userDao.find(user.getId()));

        // Clean up
        userDao.delete(user);
        userDao.close();
    }
}
