package shop;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> products;

    public Cart() {
        products = new ArrayList<> (  );
    }

    public void add(Product product) {
        products.add ( product );
    }

    public Product get(int index) { return products.get ( index ); }
}
