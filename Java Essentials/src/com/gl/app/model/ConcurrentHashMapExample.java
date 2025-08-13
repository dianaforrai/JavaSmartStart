package com.gl.app.model;

import java.util.concurrent.*;

// Task class implementing Runnable
class Task implements Runnable {
    private final ConcurrentHashMap<String, Integer> map;
    private final int taskId;

    public Task(ConcurrentHashMap<String, Integer> map, int taskId) {
        this.map = map;
        this.taskId = taskId;
    }

    @Override
    public void run() {
        String key = "Key-" + taskId;
        int value = taskId;
        map.put(key, value); // Thread-safe operation
        System.out.println("Added " + key + " by " + Thread.currentThread().getName());
    }
}

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        // Create ConcurrentHashMap
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Create ExecutorService with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Submit 100 tasks
        for (int i = 1; i <= 100; i++) {
            executor.submit(new Task(map, i));
        }

        // Shutdown executor and wait for tasks to finish
        executor.shutdown();
        try {
            if (executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("All tasks completed");
            } else {
                System.out.println("Timeout occurred before all tasks finished");
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while waiting");
            Thread.currentThread().interrupt();
        }

        // Print final size of ConcurrentHashMap
        System.out.println("Final size of ConcurrentHashMap: " + map.size());
    }
}
