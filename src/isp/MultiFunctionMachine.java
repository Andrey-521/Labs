package isp;

public class MultiFunctionMachine implements Printable, Scannable, Faxable {
    @Override
    public void print(String document) {
        System.out.println("МФУ печатает: " + document);
    }

    @Override
    public void scan(String document) {
        System.out.println("МФУ сканирует: " + document);
    }

    @Override
    public void fax(String document) {
        System.out.println("МФУ отправляет факс: " + document);
    }
}