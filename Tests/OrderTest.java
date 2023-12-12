import application.Order;
import application.Thneed;
import application.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

class OrderTest {

    private Order order;
    private Customer customer;
    private HashMap<Thneed, Integer> thneedList;

    @BeforeEach
    void setUp() {
        int exampleCustomerId = 001;
        String exampleName = "Elmo";
        String exampleAddress = "123 Sesame St";
        String examplePhoneNumber = "123-456-7890";
        ArrayList<Order> exampleOrderList = new ArrayList<>();

        customer = new Customer(exampleCustomerId, exampleName, exampleAddress, examplePhoneNumber, exampleOrderList);

        thneedList = new HashMap<>();

        order = new Order(1, thneedList, customer);
    }

    @Test
    void testIsFulfilled_WhenNotFulfilled() {
        assertFalse(order.isFulfilled(), "Order should not be fulfilled when dateFilled is null");
    }

    @Test
    void testIsFulfilled_WhenFulfilled() {
        order.setDateFilled(new Date());
        assertTrue(order.isFulfilled(), "Order should be fulfilled when dateFilled is set");
    }

    @Test
    void testGetFulfillmentTime_WhenNotFulfilled() {
        assertEquals("Order not fulfilled", order.getFulfillmentTime(), "Should return 'Order not fulfilled' when not fulfilled");
    }

    @Test
    void testGetFulfillmentTime_WhenFulfilled() {
        Date now = new Date();
        order.setDateOrdered(new Date(now.getTime() - TimeUnit.HOURS.toMillis(2)));
        order.setDateFilled(now);

        String fulfillmentTime = order.getFulfillmentTime();
        assertNotNull(fulfillmentTime, "Fulfillment time should not be null when order is fulfilled");
        assertTrue(fulfillmentTime.startsWith("0 Days, 2 Hours"), "Fulfillment time should calculate the correct duration");
    }

}
