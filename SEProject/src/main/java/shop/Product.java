package shop;

public class Product {
    private int productId;

    private String detail;
    private String name;
    private String type;
    private String email;
    private String status;


    private double price;

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public String getDetail() {
        return detail;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public double getPrice() {
        return price;
    }
    public String getStatus() {
        return status;
    }



    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override public String toString() {
        return name + " : " + detail + "\nprice : " + price + "\ntype : " + type;
    }

    public String purchaseDetail() { return name + " : " + detail + "\nprice : " + price; }
}
