package controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import shop.Cart;
import shop.Product;
import shop.ProductDao;

import java.util.ArrayList;
import java.util.List;

public class
BuyController {
    private Product product;
    private ProductDao dao;
    private ArrayList<Product> products;

    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn<Product, String> nameCol, typeCol, priceCol;

    @FXML
    public void initialize() {
        ObservableList<Product> list = FXCollections.observableArrayList ();
        nameCol = new TableColumn<> ( "name" );
        typeCol = new TableColumn<> ( "type" );
        priceCol = new TableColumn<> ( "price" );

        TableColumn<Product, Boolean> buttonCol = new TableColumn<> ( "" );
        buttonCol.setSortable ( false );

        nameCol.setCellValueFactory ( new PropertyValueFactory<> ( "name" ) );
        typeCol.setCellValueFactory ( new PropertyValueFactory<> ( "type" ) );
        priceCol.setCellValueFactory ( new PropertyValueFactory<> ( "price" ) );

        buttonCol.setCellValueFactory ( new Callback<TableColumn.CellDataFeatures<Product, Boolean>, ObservableValue<Boolean>> () {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Product, Boolean> features) {
                return new SimpleBooleanProperty ( features.getValue () != null );
            }
        } );

        buttonCol.setCellFactory ( new Callback<TableColumn<Product, Boolean>, TableCell<Product, Boolean>> () {
            @Override
            public TableCell<Product, Boolean> call(TableColumn<Product, Boolean> personBooleanTableColumn) {
                return new AddButtonCell ( tableView );
            }
        } );

        dao = new ProductDao ();
        products = dao.allProducts ();
        List<Product> productSell = new ArrayList<Product>();

        //set Sell
        for (Product p : products){
            if (p.getStatus().equals("stock")){
                productSell.add(p);
            }
        }

        for (int i = 0; i < productSell.size (); i++) {
            list.add ( productSell.get ( i ) );
        }

        tableView.setItems ( list );
        tableView.getColumns ().addAll ( nameCol, typeCol, priceCol, buttonCol );

        dao.close ();
    }

    //Open Home page
    public void homeButtonHandler(javafx.event.ActionEvent actionEvent) {
        new OpenWindow ( "mainShop.fxml", "Slampaca", 700, 560 );
        ((Node) (actionEvent.getSource ())).getScene ().getWindow ().hide ();
    }

    //Open cart page
    public void cartButtonHandler(ActionEvent actionEvent) {
        new OpenWindow ( "Cart.fxml", "Cart", 700, 560 );
        ((Node) (actionEvent.getSource ())).getScene ().getWindow ().hide ();
    }

    private class AddButtonCell extends TableCell<Product, Boolean> {
        Button detailButton = new Button ( "Detail" );
        StackPane paddedButton = new StackPane ();
        DoubleProperty buttonY = new SimpleDoubleProperty ();

        AddButtonCell(TableView table) {
            paddedButton.setPadding ( new Insets ( 3 ) );
            paddedButton.getChildren ().add ( detailButton );
            detailButton.setOnMousePressed ( new EventHandler<MouseEvent> () {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    buttonY.set ( mouseEvent.getScreenY () );
                }

            } );

            detailButton.setOnAction ( new EventHandler<ActionEvent> () {

                @Override
                public void handle(ActionEvent actionEvent) {
                    tableView.getSelectionModel ().select ( getTableRow ().getIndex () );
                    Stage detailStage = new Stage (  );
                    VBox vBox = new VBox (  );
                    Label textLabel = new Label (  );
                    Button buyButton = new Button ( "Buy" );
                    Button closeButton = new Button ( "Close" );

                    detailStage.setTitle ( "Detail Page" );
                    detailStage.setScene ( new Scene ( vBox , 400 , 300 ) );

                    vBox.getChildren ().addAll ( textLabel , buyButton , closeButton );
                    textLabel.setText ( (tableView.getItems ().get ( getIndex () ).toString () ));

                    buyButton.setOnAction ( event ->
                            {
                                dao = new ProductDao();
                                String status = "sell";
                                Product product =  tableView.getItems ().get ( getIndex () );
                                System.out.println(product.getProductId()+"");
                                dao.update (product.getProductId(),product.getDetail(),product.getName(),product.getType(),
                                        product.getPrice(),product.getEmail(),status);
                                dao.close();
                                detailStage.close ();
                                initialize();

                                System.out.println("ok");
                            }
                    ); // send to cart and close window
                    closeButton.setOnAction ( event -> detailStage.close () );

                    detailStage.show ();
                }
            } );
        }

        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay( ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }

    }
}
