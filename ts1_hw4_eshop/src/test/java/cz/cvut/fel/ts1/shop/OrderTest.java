package cz.cvut.fel.ts1.shop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void constructor_validParametersWithState_fieldsAreSetCorrectly() {
        ShoppingCart shoppingCart = new ShoppingCart();
        String customerName = "Ihor";
        String customerAdress = "Vinohradska 155/22, Praha 2";

        Order order = new Order(shoppingCart, customerName, customerAdress);

        assertEquals(customerName, order.getCustomerName());
        assertEquals(customerAdress, order.getCustomerAddress());
        assertEquals(0, order.getState());

    }

    @Test
    void constructor_validParametersWithoutState_fieldsAreSetCorrectly() {
        ShoppingCart shoppingCart = new ShoppingCart();
        String customerName = "Liza";
        String customerAdress = "Visnovka 122/41, Praha 6";
        int state = 12;

        Order order = new Order(shoppingCart, customerName, customerAdress, state);

        assertEquals(customerName, order.getCustomerName());
        assertEquals(customerAdress, order.getCustomerAddress());
        assertEquals(state, order.getState());
    }

    @Test
    void constructor_nullParameters_fieldsAllowSetNull() {
        ShoppingCart shoppingCart = new ShoppingCart();

        Order order = new Order(shoppingCart, null, null);

        assertNull(order.getCustomerName());
        assertNull(order.getCustomerAddress());
    }

}
