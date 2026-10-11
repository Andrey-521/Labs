package geometry3d;

import geometry2d.Figure;
import exceptions.ShapeException;

import java.io.Serializable;

public class Cylinder implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Figure base;
    private final double height;

    public Cylinder(Figure base, double height) throws ShapeException {
        if (base == null) {
            throw new ShapeException("Основание цилиндра не должно быть null");
        }

        if (!Double.isFinite(height) || height <= 0) {
            throw new ShapeException(
                    "Высота цилиндра должна быть положительным конечным числом"
            );
        }

        this.base = base;
        this.height = height;
    }

    public double volume() {
        return base.area() * height;
    }

    @Override
    public String toString() {
        return "Cylinder[base=" + base + ", height=" + height + "]";
    }
}