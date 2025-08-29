package com.gl;

import com.gl.dao.UserDao;
import com.gl.model.Post;
import com.gl.model.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        User u1 = new User("alice", "alice@example.com");
        Post p1 = new Post("First Post", "Alice’s first content");
        Post p2 = new Post("Second Post", "Alice’s second content");

        u1.addPost(p1);
        u1.addPost(p2);

        userDao.save(u1);

        System.out.println("Users with more than 1 post: " + userDao.findUsersWithMoreThanPosts(1));
        System.out.println("Users with no posts: " + userDao.findUsersWithNoPosts());

        userDao.close();
    }
}
