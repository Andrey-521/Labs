package org.example.lab9.task3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {

    private static final int ORDER_COUNT = 20;

    private static final int WAREHOUSE_CAPACITY = 5;

    private static final int PROCESSING_THREADS = 4;

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {

        ShoeWarehouse warehouse = new ShoeWarehouse(
                WAREHOUSE_CAPACITY,
                PROCESSING_THREADS
        );

        ExecutorService producerExecutor =
                Executors.newSingleThreadExecutor();

        try {

            Future<?> producerTask = producerExecutor.submit(() -> {

                try {
                    for (int i = 1; i <= ORDER_COUNT; i++) {

                        String shoeType =
                                ShoeWarehouse.SHOE_TYPES.get(
                                        (i - 1)
                                                % ShoeWarehouse.SHOE_TYPES.size()
                                );

                        Order order = new Order(
                                i,
                                shoeType,
                                i % 4 + 1
                        );

                        warehouse.receiveOrder(order);
                    }

                    System.out.println(
                            "Все заказы отправлены на склад."
                    );

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                    throw new RuntimeException(
                            "Отправка заказов прервана",
                            e
                    );
                }
            });

            // Прекращаем приём новых задач производителем.
            producerExecutor.shutdown();

            if (!producerExecutor.awaitTermination(
                    1,
                    TimeUnit.MINUTES
            )) {

                producerExecutor.shutdownNow();

                throw new IllegalStateException(
                        "Пул отправки заказов не завершился"
                );
            }

            // Получаем результат задачи и проверяем ошибки.
            producerTask.get();

            // После отправки всех заказов останавливаем обработчиков.
            warehouse.awaitProcessing();

            int processed = warehouse.getProcessedCount();

            System.out.println("--------------------------------");

            System.out.println(
                    "Всего создано заказов: " + ORDER_COUNT
            );

            System.out.println(
                    "Всего обработано заказов: " + processed
            );

            if (processed != ORDER_COUNT) {
                throw new IllegalStateException(
                        "Не все заказы были обработаны!"
                );
            }

            System.out.println(
                    "Все заказы успешно обработаны!"
            );

        } finally {

            producerExecutor.shutdownNow();

            warehouse.close();
        }
    }
}