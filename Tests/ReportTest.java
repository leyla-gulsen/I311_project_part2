import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import application.Report;

class ReportTest {

    private Report report;

    @BeforeEach
    void setUp() {
        report = new Report();
    }

    @Test
    void testOrdersFilledReport() {
        String result = report.ordersFilledReport();
        assertNotNull(result, "ordersFilledReport should not return null");
    }
    
    @Test
    void testMostPopularThneed() {
        String result = report.mostPopularThneed();
        assertNotNull(result, "mostPopularThneed should not return null");
    }
    
    @Test
    void testLeastPopularReport() {
        String result = report.leastPopularReport();
        assertNotNull(result, "leastPopularReport should not return null");
    }
    
    @Test
    void testTopCustomerReport() {
        String result = report.topCustomerReport();
        assertNotNull(result, "topCustomerReport should not return null");
    }
}
