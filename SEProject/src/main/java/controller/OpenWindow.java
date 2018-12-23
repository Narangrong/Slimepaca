package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenWindow {

    public OpenWindow(String name,String title,int hight,int width) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(name));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, width,  hight));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
