package id.zcode.android.nusago.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import id.zcode.android.nusago.component.ZActivity;
import id.zcode.android.nusago.component.ZCallback;
import id.zcode.android.nusago.model.Container;
import id.zcode.android.nusago.model.User;
import id.zcode.android.nusago.util.APIUtils;
import id.zcode.android.nusago.util.AppConstant;
import id.zcode.android.nusago.util.Helper;
import id.zcode.android.nusago.util.PrefManager;
import retrofit2.Call;
import retrofit2.Response;


public class Register extends ZActivity {

    private TextInputEditText txtUserId, txtPin;

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
        txtUserId = findViewById(R.id.txtUserId);
        txtPin = findViewById(R.id.txtPin);
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateRegister();
            }
        });
    }

    private void validateRegister() {
        final String userId = txtUserId.getText().toString();
        final String pin = txtPin.getText().toString();
        if (userId.isEmpty()) {
            Helper.showMessage("Nomor Handphone harus diisi");
            return;
        }
        APIUtils.getUserService(this).getUser(userId).enqueue(new ZCallback<Container<User>>() {
            @Override
            public void onResponse(Call<Container<User>> call, Response<Container<User>> response) {
                if (response.code() == 200 && response.body().getCode() == 1) {
                    User user = response.body().getData();
                    if (user.getPin().equals(pin)) {
                        user.setId(user.getId().trim());
                        PrefManager.getInstance(Register.this).putCustom(AppConstant.SP_USER, user);
                        PrefManager.getInstance(Register.this).putString(AppConstant.SP_TOKEN, userId + pin);
                        startActivity(new Intent(Register.this, Home.class));
                    } else {
                        Helper.showMessage("PIN yang Anda masukan salah");
                    }
                } else {
                    Helper.showMessage(response.body().getMessage());
                }
            }
        });
    }
}





