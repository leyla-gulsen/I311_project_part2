import application.Thneed;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ThneedTest {

    @Test
    void testThneedConstructorAndGetters() {
        String size = "medium";
        String color = "red";
        Thneed thneed = new Thneed(size, color);

        assertEquals(size, thneed.getSize(), "Size getter should return the correct size");
        assertEquals(color, thneed.getColor(), "Color getter should return the correct color");
    }

    @Test
    void testThneedEquality() {
        Thneed thneed1 = new Thneed("medium", "red");
        Thneed thneed2 = new Thneed("medium", "red");
        Thneed thneed3 = new Thneed("large", "blue");

        assertEquals(thneed1, thneed2, "Thneeds with the same size and color should be equal");
        assertNotEquals(thneed1, thneed3, "Thneeds with different sizes or colors should not be equal");
    }

    @Test
    void testThneedHashCode() {
        Thneed thneed1 = new Thneed("medium", "red");
        Thneed thneed2 = new Thneed("medium", "red");

        assertEquals(thneed1.hashCode(), thneed2.hashCode(), "Hash codes should be equal for Thneeds with the same size and color");
    }
}
