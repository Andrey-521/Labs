package ocp;

public class DiscountCalculator {
    public double calculate(DiscountStrategy strategy, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        return strategy.calculateDiscount(price);
    }
}