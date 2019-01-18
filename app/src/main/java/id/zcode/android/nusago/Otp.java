package id.zcode.android.nusago;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import id.zcode.android.nusago.model.User;
import id.zcode.android.nusago.util.APIUtils;
import id.zcode.android.nusago.util.Helper;
import id.zcode.android.nusago.util.PrefManager;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;


public class Otp extends AppCompatActivity {

    private TextInputEditText otp1, otp2, otp3, otp4;
    private TextView txtPhone;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        user = PrefManager.getInstance(this).getCustom("user", User.class);
        otp1 = findViewById(R.id.txtOtp1);
        otp2 = findViewById(R.id.txtOtp2);
        otp3 = findViewById(R.id.txtOtp3);
        otp4 = findViewById(R.id.txtOtp4);
        txtPhone = findViewById(R.id.txtPhone);
        StringBuilder sb = new StringBuilder("Ke ");
        sb.append(user.getPhone());
        txtPhone.setText(sb.toString());

        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitOtp();
            }
        });

        TextView btnResend = findViewById(R.id.btnResend);
        btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendSms();
            }
        });
    }

    private void resendSms() {
        APIUtils.getInstance(this).getUserService()
                .resendVerificationCode(user.getId());
    }

    private void submitOtp() {
        // get current user
        if (user == null) {
            Helper.showMessage(this, "User ID tidak valid");
            return;
        }

        // call api
        StringBuilder sb = new StringBuilder();
        sb.append(otp1.getText().toString())
                .append(otp2.getText().toString())
                .append(otp3.getText().toString())
                .append(otp4.getText().toString());

        Map map = new HashMap<>();
        map.put("id", user.getId());
        map.put("code", sb.toString());
        APIUtils.getInstance(this).getUserService()
                .activation(map).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() != 200) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Helper.showMessage(Otp.this, jObjError.getString("message"));
                    } catch (Exception e) {
                        Helper.showMessage(Otp.this, "Gagal menampilkan error message");
                        e.printStackTrace();
                    }
                } else {
                    Helper.showMessage(Otp.this, "Akun Anda telah aktif");
                    startActivity(new Intent(Otp.this, Home.class));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}