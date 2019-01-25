package id.zcode.android.nusago;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import id.zcode.android.nusago.component.ZActivity;
import id.zcode.android.nusago.component.ZCallback;
import id.zcode.android.nusago.model.User;
import id.zcode.android.nusago.util.APIUtils;
import id.zcode.android.nusago.util.AppConstant;
import id.zcode.android.nusago.util.Helper;
import id.zcode.android.nusago.util.PrefManager;
import retrofit2.Call;
import retrofit2.Response;


public class Register extends ZActivity {

    private TextInputEditText txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        String token = PrefManager.getInstance(this).getString(AppConstant.SP_TOKEN, "");
        if (token.length() > 0) {
            startActivity(new Intent(Register.this, Home.class));
            finish();
            return;
        }
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
        final String phone = txtPhone.getText().toString();
        if (phone.isEmpty()) {
            Helper.showMessage("Nomor Handphone harus diisi");
            return;
        }
        APIUtils.getInstance(this).getUserService().checkPhone(phone).enqueue(new ZCallback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    PrefManager.getInstance(Register.this).putCustom(AppConstant.SP_USER, response.body());
                    startActivity(new Intent(Register.this, Otp.class));
                } else {
                    TNC tnc = new TNC();
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", phone);
                    tnc.setArguments(bundle);
                    tnc.show(getSupportFragmentManager(), "tnc");
                }
            }
        });
    }
}





