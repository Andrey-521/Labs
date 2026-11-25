package org.example.lab9.task1;

public class EvenThread extends Thread {

    public EvenThread() {
        super("EvenThread");
    }

    @Override
    public void run() {
        for (int i = 2; i <= 10; i += 2) {
            System.out.println(
                    Thread.currentThread().getName() + ": " + i
            );
        }
    }
}