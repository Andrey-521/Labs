public class MyClass {

    public void publicMethodOne() {
        System.out.println("Вызван публичный метод № 1");
    }

    public void publicMethodTwo() {
        System.out.println("Вызван публичный метод № 2");
    }

    @Repeat(times = 2)
    protected void protectedMethodOne() {
        System.out.println("Вызван защищённый метод без параметров");
    }

    @Repeat(times = 1)
    protected void protectedMethodTwo(String message) {
        System.out.println("Вызван защищённый метод: " + message);
    }

    @Repeat(times = 3)
    private void privateMethodOne(String message, int number) {
        System.out.println("Вызван приватный метод: " + message + ", число = " + number);
    }

    @Repeat(times = 2)
    private void privateMethodTwo(int number) {
        System.out.println("Вызван второй приватный метод, число = " + number);
    }
}
