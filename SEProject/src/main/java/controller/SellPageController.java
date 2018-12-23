package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shop.AddProduct;
import shop.Product;
import shop.ProductDao;

import java.io.IOException;
import java.util.ArrayList;


public class SellPageController{

    private String nameIn,emailIn,typeIn,type,detailIn;
    private double priceIn;
    private Product product;
    private ProductDao dao;
    private ArrayList<Product> products;


    @FXML
    private TextField name,price,email;

    @FXML
    private TextArea detail;

    @FXML
    private SplitMenuButton typeBox;

    @FXML
    private Label nameL,priceL,emailL,detailL,typeL;

    @FXML
    private Button closeButton;


    public void cancelButtonHandler(javafx.event.ActionEvent actionEvent){
        new OpenWindow("mainShop.fxml","Slampaca",700,560);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }




    //Send Button
    public void sendButtonHandler(ActionEvent actionEvent){
        if (!checkText()){
            nameIn = name.getText();
            detailIn = detail.getText();
            priceIn = Double.parseDouble(price.getText());
            emailIn = email.getText();

            new AddProduct(nameIn,detailIn,typeIn,emailIn,priceIn);

            new OpenWindow("mainShop.fxml","Slampaca",700,560);
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

        }else{

            boolean red = false;
            boolean black = true;

            if (name.getText().equals("")){
                chageColor(nameL,red);
            }else{
                chageColor(nameL,black);
            }
            if (detail.getText().equals("")){
                chageColor(detailL,red);
            }else {
                chageColor(detailL,black);
            }
            if (price.getText().equals("")){
                chageColor(priceL,red);
            }else{
                chageColor(priceL,black);
            }
            if (email.getText().equals("")){
                chageColor(emailL,red);
            }else{
                chageColor(emailL,black);
            }
            if (typeBox.getText().equals("Pleace Select")){
                chageColor(typeL,red);
            }else{
                chageColor(typeL,black);
            }

            new OpenWindow("FillSpace.fxml","Warning",200,300);



        }
    }


    //Clear Button
    public void clearButtonHandler(javafx.event.ActionEvent actionEvent){
        name.setText("");
        detail.setText("");
        email.setText("");
        price.setText("");
        typeBox.setText("Pleace Select");
    }

    //Check Space
    private boolean checkText(){
        return (name.getText().isEmpty()|| detail.getText().isEmpty()||
                email.getText().isEmpty()|| price.getText().isEmpty() || typeBox.getText().equals("Pleace Select") );

    }

    //Color Changer
    private void chageColor(Label label,boolean color){
        if (color){
            label.setTextFill(Color.BLACK);
        }
        label.setTextFill(Color.RED);
    }



    //Close Button
    public void closeButtonHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();

        stage.close();
    }


    //Manu Box Handler
    public void starterHandler(ActionEvent actionEvent) {
        typeBox.setText("Starter");
        this.typeIn = "Starter";
    }

    public void middleHandler(ActionEvent actionEvent) {
        typeBox.setText("Middle Game");
        this.typeIn = "Middle Game";
    }

    public void lateHandler(ActionEvent actionEvent) {
        typeBox.setText("Late Game");
        this.typeIn = "Late Game";
    }
}
