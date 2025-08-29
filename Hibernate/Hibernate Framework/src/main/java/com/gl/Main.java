package com.gl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();

        // Insert users
        User user1 = new User("Alice", "alice@example.com");
        User user2 = new User("Bob", "bob@example.com");
        userDao.insert(user1);
        userDao.insert(user2);

        // Get all users
        List<User> users = userDao.getAll();
        System.out.println("All users:");
        users.forEach(System.out::println);

        // Update a user
        user1.setName("Alice Smith");
        userDao.update(user1);

        // Delete a user
        userDao.delete(user2.getId());

        // Get all users after update and delete
        System.out.println("Users after update and delete:");
        users = userDao.getAll();
        users.forEach(System.out::println);

        // Close the DAO
        userDao.close();
    }
}
