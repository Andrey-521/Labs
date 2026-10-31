public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Использование: java Main <фамилия> <имя>");
            return;
        }

        MyClass instance = new MyClass();
        Invoker invoker = new Invoker();

        System.out.println("Задание 1. Вызовы аннотированных методов:");
        invoker.invokeAnnotatedMethods(instance);

        System.out.println("\nЗадание 2. Работа с файловой системой:");
        FileSystemTask fileSystemTask = new FileSystemTask();
        fileSystemTask.run(args[0], args[1]);
    }
}
