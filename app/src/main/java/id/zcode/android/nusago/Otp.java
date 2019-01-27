package id.zcode.android.nusago;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import id.zcode.android.nusago.component.GenericTextWatcher;
import id.zcode.android.nusago.component.ZActivity;
import id.zcode.android.nusago.component.ZCallback;
import id.zcode.android.nusago.model.User;
import id.zcode.android.nusago.util.APIUtils;
import id.zcode.android.nusago.util.AppConstant;
import id.zcode.android.nusago.util.Helper;
import id.zcode.android.nusago.util.PrefManager;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Otp extends ZActivity {

    private EditText otp1, otp2, otp3, otp4, otp5;
    private TextView txtPhone;
    private User user;

    private static void setFocus(EditText editText) {
        if (editText == null)
            return;

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        user = PrefManager.getInstance(this).getCustom(AppConstant.SP_USER, User.class);

        initx();

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

    private void initx() {
        otp1 = findViewById(R.id.txtOtp1);
        otp2 = findViewById(R.id.txtOtp2);
        otp3 = findViewById(R.id.txtOtp3);
        otp4 = findViewById(R.id.txtOtp4);
        otp5 = findViewById(R.id.txtOtp5);
        txtPhone = findViewById(R.id.txtPhone);

        List<EditText> editTexts = new ArrayList<>();
        editTexts.add(otp1);
        editTexts.add(otp2);
        editTexts.add(otp3);
        editTexts.add(otp4);
        editTexts.add(otp5);
        otp1.addTextChangedListener(new GenericTextWatcher(this, otp1, editTexts));
        otp2.addTextChangedListener(new GenericTextWatcher(this, otp2, editTexts));
        otp3.addTextChangedListener(new GenericTextWatcher(this, otp3, editTexts));
        otp4.addTextChangedListener(new GenericTextWatcher(this, otp4, editTexts));
        otp5.addTextChangedListener(new GenericTextWatcher(this, otp5, editTexts));

    }

    private void resendSms() {
        APIUtils.getInstance(this).getAuthService()
                .resendVerificationCode(user.getId()).enqueue(new ZCallback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200)
                    Helper.showMessage("Silahkan cek kembali SMS yang telah kami kirimkan ke " + user.getPhone());
            }
        });
    }

    private void submitOtp() {
        // get current user
        if (user == null) {
            Helper.showMessage("User ID tidak valid");
            return;
        }

        // call api
        StringBuilder sb = new StringBuilder();
        sb.append(otp1.getText().toString())
                .append(otp2.getText().toString())
                .append(otp3.getText().toString())
                .append(otp4.getText().toString())
                .append(otp5.getText().toString());

        Map map = new HashMap<>();
        map.put("id", user.getId());
        map.put("code", sb.toString());
        APIUtils.getInstance(this).getAuthService()
                .activation(map).enqueue(new ZCallback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    String token = response.body();
                    PrefManager.getInstance(Otp.this).putString(AppConstant.SP_TOKEN, token);
                    startActivity(new Intent(Otp.this, Home.class));
                }
            }
        });
    }

}