package id.zcode.android.nusago.model;

import com.google.gson.annotations.SerializedName;

public class Container<T> {
    @SerializedName("kode")
    private int code;

    @SerializedName("keterangan")
    private String message;

    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
