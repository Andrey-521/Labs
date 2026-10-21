package geometry3d;

import exceptions.InvalidFigureParameterException;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {
    private static final double EPS = 1e-9;

    @Test
    void volumeWithCircleBase() {
        Figure base = new Circle(2.0);
        Cylinder c = new Cylinder(base, 5.0);
        double expected = Math.PI * 2.0 * 2.0 * 5.0;
        assertEquals(expected, c.volume(), EPS);
    }

    @Test
    void volumeWithRectangleBase() {
        Figure base = new Rectangle(3.0, 4.0);
        Cylinder c = new Cylinder(base, 2.5);
        double expected = 3.0 * 4.0 * 2.5;
        assertEquals(expected, c.volume(), EPS);
    }

    @Test
    void volumeWithDifferentHeight() {
        Figure base = new Circle(1.0);
        Cylinder c = new Cylinder(base, 10.0);
        double expected = Math.PI * 1.0 * 1.0 * 10.0;
        assertEquals(expected, c.volume(), EPS);
    }

    @Test
    void constructorRejectsNullBase() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Cylinder(null, 5.0));
    }

    @Test
    void constructorRejectsZeroHeight() {
        Figure base = new Circle(2.0);
        assertThrows(InvalidFigureParameterException.class,
                () -> new Cylinder(base, 0.0));
    }

    @Test
    void constructorRejectsNegativeHeight() {
        Figure base = new Circle(2.0);
        assertThrows(InvalidFigureParameterException.class,
                () -> new Cylinder(base, -1.0));
    }

    @Test
    void constructorRejectsNaNHeight() {
        Figure base = new Circle(2.0);
        assertThrows(InvalidFigureParameterException.class,
                () -> new Cylinder(base, Double.NaN));
    }

    @Test
    void constructorRejectsPositiveInfinityHeight() {
        Figure base = new Circle(2.0);
        assertThrows(InvalidFigureParameterException.class,
                () -> new Cylinder(base, Double.POSITIVE_INFINITY));
    }

    @Test
    void constructorRejectsNegativeInfinityHeight() {
        Figure base = new Circle(2.0);
        assertThrows(InvalidFigureParameterException.class,
                () -> new Cylinder(base, Double.NEGATIVE_INFINITY));
    }

    @Test
    void getBaseReturnsSameObject() {
        Figure base = new Circle(2.0);
        Cylinder c = new Cylinder(base, 5.0);
        assertSame(base, c.getBase());
    }

    @Test
    void getHeightReturnsConstructorValue() {
        Figure base = new Circle(2.0);
        Cylinder c = new Cylinder(base, 5.0);
        assertEquals(5.0, c.getHeight(), EPS);
    }

    @Test
    void toStringContainsTypeBaseAndHeight() {
        Figure base = new Circle(2.0);
        Cylinder c = new Cylinder(base, 5.0);
        String s = c.toString();
        assertTrue(s.contains("Cylinder"));
        assertTrue(s.contains("Circle"));
        assertTrue(s.contains("5.0"));
    }
}