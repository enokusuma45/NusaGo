package id.zcode.android.nusago.model;

public class HistoryTrans {

    private String nama;
    private String TransDate;


    public HistoryTrans(String nama, String TransDate) {
        this.nama = nama;
        this.TransDate = TransDate;


    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTransDate() {
        return TransDate;
    }

    public void setTransDate(String TransDate) {
        this.TransDate = TransDate;
    }

}
