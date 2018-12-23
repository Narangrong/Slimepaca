package shop;

import java.util.ArrayList;

public class AddProduct {
    private ProductDao dao;
    private ArrayList<Product> products;
    private Product product;

    public AddProduct(String name,String detail,String type,String email,double price) {

        product = new Product();
        dao = new ProductDao();
        products = dao.allProducts();

        int id = products.get(products.size() - 1).getProductId()+1;

        product.setName(name);
        product.setDetail(detail);
        product.setType(type);
        product.setEmail(email);
        product.setPrice(price);
        product.setProductId(id);
        product.setStatus("stock");

        dao.add(product);
        dao.close();

        System.out.println("Add success");
    }
}
