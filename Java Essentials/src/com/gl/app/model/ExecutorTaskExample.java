package com.gl.app.model;

import java.util.concurrent.*;
import java.util.Random;

class Task implements Runnable {
    private static final Random random = new Random();

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Task started by " + threadName);
        try {
            // Sleep for a random time between 1 and 5 seconds
            int sleepTime = 1 + random.nextInt(5);
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println("Task interrupted in " + threadName);
            Thread.currentThread().interrupt();
        }
        System.out.println("Task finished by " + threadName);
    }
}

public class ExecutorTaskExample {
    public static void main(String[] args) {
        int poolSize = 5;
        int numberOfTasks = 20;

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        // Submit 20 tasks
        for (int i = 0; i < numberOfTasks; i++) {
            executor.submit(new Task());
        }

        // Shutdown the executor to stop accepting new tasks
        executor.shutdown();

        try {
            // Wait for all tasks to finish, max 1 minute
            if (executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("All tasks finished");
            } else {
                System.out.println("Timeout occurred before all tasks finished");
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while waiting");
            Thread.currentThread().interrupt();
        }
    }
}
