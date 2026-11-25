package org.example.lab9.task1;

public class OddRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 9; i += 2) {
            System.out.println(
                    Thread.currentThread().getName() + ": " + i
            );
        }
    }
}