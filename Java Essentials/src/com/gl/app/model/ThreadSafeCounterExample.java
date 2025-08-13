package com.gl.app.model;

class Counter {
    private int count = 0;

    // Thread-safe increment method
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class ThreadSafeCounterExample {
    public static void main(String[] args) {
        Counter counter = new Counter();

        // First thread
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        // Second thread
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // Wait for both to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final count should be 2000
        System.out.println("Final count: " + counter.getCount());
    }
}
