package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MainShopController {
    private Stage stage;

    @FXML private TextField search;
    @FXML private Label recomment;
    @FXML private Button closeButton;

    //Open Buy Page
    public void buyButtonHandler(javafx.event.ActionEvent actionEvent) {
        new OpenWindow("Buy.fxml","Buy",700,560);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

    }

    //Open Cart page
    public void cartButtonHandler(javafx.event.ActionEvent actionEvent){
        new OpenWindow("Cart.fxml","Cart",700,560);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

    }

    //Open Sell page
    public void sellButtonHandler(javafx.event.ActionEvent actionEvent){
        new OpenWindow("SellPage.fxml","SellPage",700,560);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }




    //Contract us
    public void contractButtonHandler(javafx.event.ActionEvent actionEvent){
        new OpenWindow("Contact.fxml","Contract",200,300);

    }


    //Close Contract us
    public void closeButtonHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();

        stage.close();
    }

}

