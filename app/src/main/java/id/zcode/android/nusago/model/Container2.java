package id.zcode.android.nusago.model;

import java.util.ArrayList;
import java.util.List;

public class Container2<T> {
    private String status;

    private List<T> data = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
