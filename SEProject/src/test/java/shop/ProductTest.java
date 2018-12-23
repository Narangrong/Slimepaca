package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;

    @BeforeEach
    void init() {
        product = new Product ();
    }

    @Test
    void get_and_set_ProductId() {
        product.setProductId ( 1 );
        assertEquals ( 1 , product.getProductId () );
    }

    @Test
    void get_and_set_Detail() {
        product.setDetail ( "test" );
        assertEquals ( "test" , product.getDetail () );
    }

    @Test
    void get_and_set_Name() {
        product.setName ( "Test" );
        assertEquals ( "Test" , product.getName () );
    }

    @Test
    void get_and_set_Type() {
        product.setType ( "starter" );
        assertEquals ( "starter" , product.getType () );
    }

    @Test
    void get_and_set_Email() {
        product.setEmail ( "xxx@gmail.com" );
        assertEquals ( "xxx@gmail.com" , product.getEmail () );
    }

    @Test
    void get_and_set_Price() {
        product.setPrice ( 100 );
        assertEquals ( 100 , product.getPrice () );
    }

    @Test
    void get_and_set_Status() {
        product.setStatus ( "sell" );
        assertEquals ( "sell" , product.getStatus () );
    }
}