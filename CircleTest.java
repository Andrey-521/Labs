package geometry2d;

import exceptions.InvalidFigureParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {
    private static final double EPS = 1e-9;

    @Test
    void areaOfUnitCircleIsPi() {
        Circle c = new Circle(1.0);
        assertEquals(Math.PI, c.area(), EPS);
    }

    @Test
    void perimeterOfUnitCircleIsTwoPi() {
        Circle c = new Circle(1.0);
        assertEquals(2 * Math.PI, c.perimeter(), EPS);
    }

    @Test
    void areaOfCircleWithRadiusTwoPointFive() {
        Circle c = new Circle(2.5);
        assertEquals(Math.PI * 2.5 * 2.5, c.area(), EPS);
    }

    @Test
    void perimeterOfCircleWithRadiusTwoPointFive() {
        Circle c = new Circle(2.5);
        assertEquals(2 * Math.PI * 2.5, c.perimeter(), EPS);
    }

    @Test
    void areaOfSmallCircle() {
        Circle c = new Circle(0.5);
        assertEquals(Math.PI * 0.25, c.area(), EPS);
    }

    @Test
    void constructorRejectsZeroRadius() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Circle(0.0));
    }

    @Test
    void constructorRejectsNegativeRadius() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Circle(-1.0));
    }

    @Test
    void constructorRejectsNaNRadius() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Circle(Double.NaN));
    }

    @Test
    void constructorRejectsPositiveInfinityRadius() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Circle(Double.POSITIVE_INFINITY));
    }

    @Test
    void constructorRejectsNegativeInfinityRadius() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Circle(Double.NEGATIVE_INFINITY));
    }

    @Test
    void getterReturnsConstructorValue() {
        Circle c = new Circle(2.5);
        assertEquals(2.5, c.getRadius(), EPS);
    }

    @Test
    void toStringContainsTypeAndRadius() {
        Circle c = new Circle(2.5);
        String s = c.toString();
        assertTrue(s.contains("Circle"));
        assertTrue(s.contains("2.5"));
    }
}
