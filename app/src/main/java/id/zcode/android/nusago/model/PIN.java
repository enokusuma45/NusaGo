package id.zcode.android.nusago.model;

import java.util.Date;

public class PIN {
    private String value;
    private Date expired;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }
}
