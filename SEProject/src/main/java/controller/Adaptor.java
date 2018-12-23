package controller;

import shop.Product;

import java.util.ArrayList;

public class Adaptor {
    private static ArrayList<Product> products;

    public Adaptor() {
        products = new ArrayList<Product>();
    }

    public void addProduct(Product product){
        products.add(product);
        System.out.println("add Item to cart.");
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public Product getProduct(int index) {
        return products.get ( index );
    }

    public int getSize() {
        return products.size ();
    }

}
