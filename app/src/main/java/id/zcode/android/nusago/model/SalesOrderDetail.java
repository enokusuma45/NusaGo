package id.zcode.android.nusago.model;

import com.google.gson.annotations.SerializedName;

public class SalesOrderDetail {
    private String id;
    @SerializedName("qty")
    private int quantity;
    @SerializedName("hrgjual")
    private double price;
    @SerializedName("nm_brg")
    private String productName;
    @SerializedName("id_trxsale")
    private String soCode;
    private SalesOrder salesOrder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSoCode() {
        return soCode;
    }

    public void setSoCode(String soCode) {
        this.soCode = soCode;
    }
}

