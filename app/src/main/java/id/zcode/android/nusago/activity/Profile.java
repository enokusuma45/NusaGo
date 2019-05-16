package id.zcode.android.nusago.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import id.zcode.android.nusago.model.User;
import id.zcode.android.nusago.util.AppConstant;
import id.zcode.android.nusago.util.PrefManager;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
    }

    private void init() {
        TextView txtName = findViewById(R.id.txtName),
                txtPhone = findViewById(R.id.txtPhone),
                txtKtp = findViewById(R.id.txtKtp),
                txtAddress = findViewById(R.id.txtAddress),
                txtFactory = findViewById(R.id.txtFactory);

        User user = PrefManager.getInstance(this).getCustom(AppConstant.SP_USER, User.class);
        if (user == null) return;
        String factory = user.getFactory() != null ? user.getFactory().getName() : "-";
        String name = user.getName() == null ? "-" : user.getName();
        String phone = user.getPhone() == null ? "-" : user.getPhone();
        String ktp = user.getKtp() == null ? "-" : user.getKtp();
        String address = user.getAddress() == null ? "-" : user.getAddress();

        txtName.setText(name);
        txtPhone.setText(phone);
        txtKtp.setText(ktp);
        txtAddress.setText(address);
        txtFactory.setText(factory);
    }

    public void logout(View view) {
        // remove shared preference
        PrefManager.getInstance(this).cleanUp();
        startActivity(new Intent(this, Register.class));
        finish();
    }

    public void createPin(View view) {
        startActivity(new Intent(this, BuatPin.class));
    }
}
