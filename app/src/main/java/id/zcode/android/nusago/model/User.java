package id.zcode.android.nusago.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id_cust")
    private String id;
    private String ktp;
    @SerializedName("no_hp")
    private String phone;
    @SerializedName("nm_customer")
    private String name;
    @SerializedName("address1")
    private String address;
    @SerializedName("sisa")
    private double saldo;
    private Factory factory;
    private String pin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
