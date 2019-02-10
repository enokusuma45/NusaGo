package id.zcode.android.nusago.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesOrder {
    private String id;
    private Date date;
    private String code;
    private int status;
    private String notes;
    private User customer;
    private Store store;
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

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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
}
