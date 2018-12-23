package shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection connection;

    public ProductDao(){
        ConnectDB connectDB = new ConnectDB();
        this.connection = connectDB.connect();
    }


    //Close DB
    public void close(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Add New Product
    public void add(Product product){
        String sqlText = "INSERT INTO product(prod_id, detail, name, type, price, email,status) VALUES (?, ?, ?, ?, ? ,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlText);
            preparedStatement.setInt(1,product.getProductId());
            preparedStatement.setString(2,product.getDetail());
            preparedStatement.setString(3,product.getName());
            preparedStatement.setString(4,product.getType());
            preparedStatement.setDouble(5,product.getPrice());
            preparedStatement.setString(6,product.getEmail());
            preparedStatement.setString(7,product.getStatus());

            preparedStatement.execute();
            preparedStatement.close();

            System.out.print("\nThis is product's stock.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Delete Product
    public int delete(Product product){
        int result = 0;
        String sqlText = "DELET FROM product WHERE prod_id=?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlText);
            preparedStatement.setInt(1,product.getProductId());
            result = preparedStatement.executeUpdate();

            System.out.print("Result = " +result);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    //Get Primary key list
    public void pkList(){
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            try (ResultSet tables = databaseMetaData.getTables(null, null, "%", new String[] { "TABLE" })) {
                while (tables.next()) {
                    String catalog = tables.getString("TABLE_CAT");
                    String schema = tables.getString("TABLE_SCHEM");
                    String tableName = tables.getString("TABLE_NAME");
                    System.out.println("Table: " + tableName);
                    try (ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(catalog, schema, tableName)) {
                        while (primaryKeys.next()) {
                            System.out.println("Primary key: " + primaryKeys.getRow());
                        }
                    }
                    // similar for exportedKeys
                }

        }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //All Product
    public ArrayList<Product> allProducts(){
        ArrayList<Product> products = new ArrayList<>();
        Product product;
        String sqlText = "SELECT * FROM product";

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlText);

            while(resultSet.next()){
                product = new Product();
                product.setName ( resultSet.getString ( "name" ) );
                product.setProductId(resultSet.getInt("prod_id"));
                product.setDetail(resultSet.getString("detail"));
                product.setEmail(resultSet.getString("email"));
                product.setType(resultSet.getString("type"));
                product.setPrice(resultSet.getDouble("price"));
                product.setStatus(resultSet.getString("status"));
                products.add(product);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    //update
    public void update(int id,String detail,String name,String type, double price,String email,String status){
        String sqlText = "UPDATE product SET detail = ? , name = ? , type = ? , price = ? , email = ?, status = ? WHERE prod_id = ?";

        try {
            // set the corresponding param
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlText);

            preparedStatement.setString(1,detail);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,type);
            preparedStatement.setDouble(4,price);
            preparedStatement.setString(5,email);
            preparedStatement.setString(6,status);

            preparedStatement.setInt(7,id);

            // update
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
