package com.frauddetection.util;

/**
 * Runnable class simulating multithreaded fraud checks.
 * Demonstrates: Multithreading, Synchronization, Exception Handling.
 */
public class FraudProcessor implements Runnable {
    private static int progress = 0;

    /**
     * Simulates background fraud processing and thread-safe progress.
     */
    @Override
    public void run() {
        synchronized (FraudProcessor.class) { // Synchronization demo
            try {
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(500); // simulate processing
                    progress += 33;
                }
                System.out.println("Fraud check completed in background, progress: " + progress + "%");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("FraudProcessor interrupted: " + e.getMessage());
            }
        }
    }
}
