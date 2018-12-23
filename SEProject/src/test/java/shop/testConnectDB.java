package shop;

public class testConnectDB {
    public static void main(String[] args) {
        Product product = new Product( );

        ProductDao productDao = new ProductDao();
        productDao.add(product);
        productDao.close();

    }
}
