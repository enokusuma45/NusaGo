package id.zcode.android.nusago.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        txtName.setText(user.getName());
        txtPhone.setText(user.getPhone());
        txtKtp.setText(user.getKtp());
        txtAddress.setText(user.getAddress());
        if (user.getFactory() != null)
            txtFactory.setText(user.getFactory().getName());
    }
}
