package lsp;

public class Sparrow extends FlyingBird {
    @Override
    public void eat() {
        System.out.println("Воробей клюёт семечки.");
    }

    @Override
    public void fly() {
        System.out.println("Воробей летит.");
    }
}
