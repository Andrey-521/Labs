package org.example.lab9.task1;

public class ThreadDemo {

    public static void main(String[] args)
            throws InterruptedException {

        Thread evenThread = new EvenThread();

        Thread oddThread = new Thread(
                new OddRunnable(),
                "OddThread"
        );

        evenThread.start();
        oddThread.start();

        evenThread.join();
        oddThread.join();

        System.out.println("Все потоки завершены.");
    }
}