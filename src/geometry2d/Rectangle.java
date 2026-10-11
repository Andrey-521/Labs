package geometry2d;

import exceptions.ShapeException;

public class Rectangle implements Figure {
    private static final long serialVersionUID = 1L;

    private final double width;
    private final double height;

    public Rectangle(double width, double height) throws ShapeException {
        if (!Double.isFinite(width) || width <= 0
                || !Double.isFinite(height) || height <= 0) {
            throw new ShapeException(
                    "Ширина и высота должны быть положительными конечными числами"
            );
        }

        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Rectangle[width=" + width + ", height=" + height + "]";
    }
}
