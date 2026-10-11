package geometry2d;

import exceptions.InvalidFigureParameterException;

public class Rectangle implements Figure {
    private static final long serialVersionUID = 1L;

    private final double sideA;
    private final double sideB;

    public Rectangle(double sideA, double sideB) {
        if (!Double.isFinite(sideA) || sideA <= 0) {
            throw new InvalidFigureParameterException(
                    "sideA",
                    "должна быть положительным конечным числом, получено " + sideA
            );
        }
        if (!Double.isFinite(sideB) || sideB <= 0) {
            throw new InvalidFigureParameterException(
                    "sideB",
                    "должна быть положительным конечным числом, получено " + sideB
            );
        }

        this.sideA = sideA;
        this.sideB = sideB;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    @Override
    public double area() {
        return sideA * sideB;
    }

    @Override
    public double perimeter() {
        return 2 * (sideA + sideB);
    }

    @Override
    public String toString() {
        return "Rectangle{sideA=" + sideA + ", sideB=" + sideB + "}";
    }
}
