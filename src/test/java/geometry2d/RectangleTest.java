package geometry2d;

import exceptions.InvalidFigureParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    private static final double EPS = 1e-9;

    @Test
    void areaOfThreeByFourRectangleIsTwelve() {
        Rectangle r = new Rectangle(3.0, 4.0);
        assertEquals(12.0, r.area(), EPS);
    }

    @Test
    void perimeterOfThreeByFourRectangleIsFourteen() {
        Rectangle r = new Rectangle(3.0, 4.0);
        assertEquals(14.0, r.perimeter(), EPS);
    }

    @Test
    void areaOfSquareWithSideFiveIsTwentyFive() {
        Rectangle r = new Rectangle(5.0, 5.0);
        assertEquals(25.0, r.area(), EPS);
    }

    @Test
    void perimeterOfSquareWithSideFiveIsTwenty() {
        Rectangle r = new Rectangle(5.0, 5.0);
        assertEquals(20.0, r.perimeter(), EPS);
    }

    @Test
    void areaOfRectangleWithDifferentSides() {
        Rectangle r = new Rectangle(2.5, 4.0);
        assertEquals(10.0, r.area(), EPS);
    }

    @Test
    void perimeterOfRectangleWithDifferentSides() {
        Rectangle r = new Rectangle(2.5, 4.0);
        assertEquals(13.0, r.perimeter(), EPS);
    }

    @Test
    void constructorRejectsZeroSideA() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Rectangle(0.0, 4.0));
    }

    @Test
    void constructorRejectsNegativeSideA() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Rectangle(-1.0, 4.0));
    }

    @Test
    void constructorRejectsNaNSideA() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Rectangle(Double.NaN, 4.0));
    }

    @Test
    void constructorRejectsPositiveInfinitySideA() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Rectangle(Double.POSITIVE_INFINITY, 4.0));
    }

    @Test
    void constructorRejectsNegativeInfinitySideA() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Rectangle(Double.NEGATIVE_INFINITY, 4.0));
    }

    @Test
    void constructorRejectsZeroSideB() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Rectangle(4.0, 0.0));
    }

    @Test
    void constructorRejectsNegativeSideB() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Rectangle(4.0, -1.0));
    }

    @Test
    void constructorRejectsNaNSideB() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Rectangle(4.0, Double.NaN));
    }

    @Test
    void constructorRejectsPositiveInfinitySideB() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Rectangle(4.0, Double.POSITIVE_INFINITY));
    }

    @Test
    void constructorRejectsNegativeInfinitySideB() {
        assertThrows(InvalidFigureParameterException.class,
                () -> new Rectangle(4.0, Double.NEGATIVE_INFINITY));
    }

    @Test
    void gettersReturnConstructorValues() {
        Rectangle r = new Rectangle(3.0, 4.0);
        assertEquals(3.0, r.getSideA(), EPS);
        assertEquals(4.0, r.getSideB(), EPS);
    }

    @Test
    void toStringContainsTypeAndSides() {
        Rectangle r = new Rectangle(3.0, 4.0);
        String s = r.toString();
        assertTrue(s.contains("Rectangle"));
        assertTrue(s.contains("3.0"));
        assertTrue(s.contains("4.0"));
    }
}
