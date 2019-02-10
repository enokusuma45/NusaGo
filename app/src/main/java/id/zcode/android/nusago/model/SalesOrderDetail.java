package id.zcode.android.nusago.model;

public class SalesOrderDetail {
    private String id;
    private int quantity;
    private double price;
    private String productName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesOrderDetail)) return false;
        return id != null && id.equals(((SalesOrderDetail) o).id);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

