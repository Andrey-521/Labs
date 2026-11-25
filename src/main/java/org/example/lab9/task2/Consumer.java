package org.example.lab9.task2;

public class Consumer implements Runnable {

    private final ShoeWarehouse warehouse;

    private final int ordersToProcess;

    public Consumer(
            ShoeWarehouse warehouse,
            int ordersToProcess
    ) {
        this.warehouse = warehouse;
        this.ordersToProcess = ordersToProcess;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < ordersToProcess; i++) {

                Order order = warehouse.fulfillOrder();

                System.out.println(
                        Thread.currentThread().getName()
                                + " обработал заказ #"
                                + order.id()
                                + " | " + order.shoeType()
                                + " | Количество: "
                                + order.quantity()
                );
            }

            System.out.println(
                    Thread.currentThread().getName()
                            + " завершил работу."
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            System.err.println(
                    Thread.currentThread().getName()
                            + " был прерван."
            );
        }
    }
}