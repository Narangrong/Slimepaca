package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    Cart cart;
    Product product1;

    @BeforeEach
    void init() {
        cart = new Cart ();
        product1 = new Product ();
    }

    @Test
    void add_and_get() {
        cart.add ( product1 );
        assertEquals ( cart.get ( 0 ) , product1);
    }

}