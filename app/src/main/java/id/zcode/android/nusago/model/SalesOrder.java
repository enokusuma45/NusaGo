package id.zcode.android.nusago.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesOrder {
    private String id;
    @SerializedName("tgl")
    private Date date;
    @SerializedName("id_trxsale")
    private String code;
    private int status;
    private String notes;
    private User customer;
    @SerializedName("id_outlet")
    private String store;
    private double total;
    private List<SalesOrderDetail> salesOrderDetails = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<SalesOrderDetail> getSalesOrderDetails() {
        return salesOrderDetails;
    }

    public void setSalesOrderDetails(List<SalesOrderDetail> salesOrderDetails) {
        this.salesOrderDetails = salesOrderDetails;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addDetail(SalesOrderDetail salesOrderDetail) {
        salesOrderDetails.add(salesOrderDetail);
        salesOrderDetail.setSalesOrder(this);
    }

    public void removeDetail(SalesOrderDetail salesOrderDetail) {
        salesOrderDetails.remove(salesOrderDetail);
        salesOrderDetail.setSalesOrder(null);
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
