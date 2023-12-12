import application.Customer;
import application.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class CustomerTest {

    @Test
    void testCustomerGettersAndSetters() {
        int customerId = 1;
        String name = "Elmo";
        String address = "123 Sesame St";
        String phoneNumber = "123-4567";
        ArrayList<Order> orderList = new ArrayList<>();

        Customer customer = new Customer(customerId, name, address, phoneNumber, orderList);

        assertEquals(customerId, customer.getCustomerId());
        assertEquals(name, customer.getName());
        assertEquals(address, customer.getAddress());
        assertEquals(phoneNumber, customer.getPhoneNumber());
        assertEquals(orderList, customer.getOrderList());
    }

    @Test
    void testCustomerEquals() {
        Customer customer1 = new Customer(1, "Elmo", "123 Sesame St", "123-4567", new ArrayList<>());
        Customer customer2 = new Customer(2, "Elmo", "123 Sesame St", "123-4567", new ArrayList<>());
        Customer customer3 = new Customer(3, "Big Bird", "456 Main St", "456-7890", new ArrayList<>());

        assertTrue(customer1.equals(customer2), "Customers with the same name, address, and phone number should be equal");
        assertFalse(customer1.equals(customer3), "Customers with different details should not be equal");
    }

}
