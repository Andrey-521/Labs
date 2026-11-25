package org.example.lab9.task3;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ShoeWarehouse implements AutoCloseable {

    public static final List<String> SHOE_TYPES = List.of(
            "Nike",
            "Adidas",
            "Puma",
            "New Balance",
            "Reebok"
    );

    private final ArrayBlockingQueue<WorkItem> orders;

    private final ExecutorService processingExecutor;

    private final int consumerCount;

    private final AtomicInteger processedCount =
            new AtomicInteger(0);

    private final AtomicBoolean acceptingOrders =
            new AtomicBoolean(true);

    private final AtomicBoolean shutdownStarted =
            new AtomicBoolean(false);

    private record WorkItem(Order order, boolean poison) {

        static WorkItem order(Order order) {
            return new WorkItem(order, false);
        }

        static WorkItem poisonPill() {
            return new WorkItem(null, true);
        }
    }

    public ShoeWarehouse(
            int capacity,
            int consumerCount
    ) {
        if (capacity <= 0 || consumerCount <= 0) {
            throw new IllegalArgumentException(
                    "Вместимость и число потребителей "
                            + "должны быть положительными"
            );
        }

        this.orders = new ArrayBlockingQueue<>(capacity);

        this.consumerCount = consumerCount;

        this.processingExecutor =
                Executors.newFixedThreadPool(consumerCount);

        for (int i = 0; i < consumerCount; i++) {

            processingExecutor.submit(
                    this::processOrders
            );
        }
    }

    public void receiveOrder(Order order)
            throws InterruptedException {

        if (!acceptingOrders.get()) {
            throw new IllegalStateException(
                    "Склад больше не принимает заказы"
            );
        }

        orders.put(WorkItem.order(order));

        System.out.println(
                "Склад принял заказ #" + order.id()
                        + " | " + order.shoeType()
        );
    }

    private void processOrders() {

        try {
            while (true) {

                WorkItem item = orders.take();

                if (item.poison()) {
                    System.out.println(
                            Thread.currentThread().getName()
                                    + " завершает работу."
                    );

                    break;
                }

                Order order = item.order();

                System.out.println(
                        Thread.currentThread().getName()
                                + " обрабатывает заказ #"
                                + order.id()
                                + " | " + order.shoeType()
                                + " | Количество: "
                                + order.quantity()
                );

                processedCount.incrementAndGet();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            System.err.println(
                    Thread.currentThread().getName()
                            + " был прерван."
            );
        }
    }

    public int getProcessedCount() {
        return processedCount.get();
    }

    public void awaitProcessing()
            throws InterruptedException {

        if (shutdownStarted.compareAndSet(false, true)) {

            acceptingOrders.set(false);

            // Отправляем сигнал остановки каждому потребителю.
            for (int i = 0; i < consumerCount; i++) {
                orders.put(WorkItem.poisonPill());
            }

            processingExecutor.shutdown();
        }

        if (!processingExecutor.awaitTermination(
                1,
                TimeUnit.MINUTES
        )) {

            processingExecutor.shutdownNow();

            if (!processingExecutor.awaitTermination(
                    1,
                    TimeUnit.MINUTES
            )) {
                throw new IllegalStateException(
                        "Пул обработки не завершился"
                );
            }
        }
    }

    @Override
    public void close() throws InterruptedException {
        awaitProcessing();
    }
}