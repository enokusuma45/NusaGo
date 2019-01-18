package id.zcode.android.nusago;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import id.zcode.android.nusago.model.User;
import id.zcode.android.nusago.util.Helper;
import id.zcode.android.nusago.util.PrefManager;


public class Register extends AppCompatActivity {

    private TextInputEditText txtKtp, txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtKtp = findViewById(R.id.txtKtp);
        txtPhone = findViewById(R.id.txtPhone);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTNC();
            }
        });
    }

    private void showTNC() {
        String ktp = txtKtp.getText().toString();
        String phone = txtPhone.getText().toString();
        final View view = findViewById(android.R.id.content);
        if (ktp.isEmpty()) {
            Helper.showMessage(view, "KTP harus diisi");
            return;
        }
        if (phone.isEmpty()) {
            Helper.showMessage(view, "Nomor Handphone harus diisi");
            return;
        }
        User user = new User();
        user.setKtp(ktp);
        user.setPhone(phone);
        PrefManager.getInstance(this).putCustom("user", user);
        TNC tnc = new TNC();
        tnc.show(getSupportFragmentManager(), "tnc");
    }
}





