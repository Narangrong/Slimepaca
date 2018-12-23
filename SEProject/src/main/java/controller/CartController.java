package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shop.Product;
import shop.ProductDao;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CartController {
    private Product product;
    private ProductDao dao;
    private ArrayList<Product> products , productInCart;

    @FXML private Label totalPurchase;
    @FXML private TextArea totalProduct;
    @FXML private Button clearButton;

    @FXML
    public void initialize(){
        dao = new ProductDao ();
        products = dao.allProducts ();
        productInCart = new ArrayList<Product>();
        totalPurchase.setText ( "0.0 ฿" );
        String text = "";
        int totalPrice = 0;

        //Product in cart
        for (Product p : products){
            if (p.getStatus().equals("sell")){
                productInCart.add(p);
            }
        }

        if (productInCart.size () == 0) {
            totalProduct.setText ( "No item in cart." );
        } else {

            for (int i = 0; i < productInCart.size (); i++) {
                text = text + productInCart.get ( i ).purchaseDetail () + "\n\n";
                totalPrice += productInCart.get ( i ).getPrice ();
            }

            totalProduct.setText ( text );
        }

        totalPurchase.setText ( totalPrice + " ฿" );
        dao.close();

    }

    //Open Home page
    public void homeButtonHandler(javafx.event.ActionEvent actionEvent){
        new OpenWindow("mainShop.fxml","Slampaca",700,560);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

    }

    //Open Buy page
    public void conShopButtonHandler(javafx.event.ActionEvent actionEvent) {
        new OpenWindow("Buy.fxml","Buy",700,560);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void clearButtonHandler(javafx.event.ActionEvent event) {
        dao = new ProductDao ();

        for (Product p : productInCart) {
            dao.update (p.getProductId (),p.getDetail (),p.getName (),p.getType (),p.getPrice (),p.getEmail (),"stock");

        }

        totalPurchase.setText ( "0 ฿" );
        totalProduct.setText ( "No item in cart." );


        dao.close ();
    }
}
