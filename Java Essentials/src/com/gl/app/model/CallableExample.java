package com.gl.app.model;

import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

// Task class implementing Callable<Integer>
class Task implements Callable<Integer> {
    private final int number;

    public Task(int number) {
        this.number = number;
    }

    @Override
    public Integer call() {
        int sum = 0;
        for (int i = 1; i <= number; i++) {
            sum += i;
        }
        System.out.println("Sum up to " + number + " calculated by " + Thread.currentThread().getName());
        return sum;
    }
}

public class CallableExample {
    public static void main(String[] args) {
        int poolSize = 5;
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        List<Future<Integer>> futures = new ArrayList<>();
        int[] numbers = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        // Submit tasks
        for (int num : numbers) {
            Future<Integer> future = executor.submit(new Task(num));
            futures.add(future);
        }

        // Shutdown the executor
        executor.shutdown();

        // Retrieve results from futures
        for (int i = 0; i < futures.size(); i++) {
            try {
                Integer result = futures.get(i).get(); // waits if necessary
                System.out.println("Sum for " + numbers[i] + " = " + result);
            } catch (InterruptedException e) {
                System.out.println("Task interrupted while waiting for result");
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                System.out.println("Exception occurred during task execution: " + e.getCause());
            }
        }
    }
}
