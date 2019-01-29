package id.zcode.android.nusago.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import id.zcode.android.nusago.component.ZCallback;
import id.zcode.android.nusago.model.PIN;
import id.zcode.android.nusago.model.User;
import id.zcode.android.nusago.util.APIUtils;
import id.zcode.android.nusago.util.AppConstant;
import id.zcode.android.nusago.util.PrefManager;
import retrofit2.Call;
import retrofit2.Response;

import java.util.Date;

public class BuatPin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pin);
        init();
    }

    private void init() {
        final TextView txtPin = findViewById(R.id.txtPin),
                lblTimer = findViewById(R.id.lblTimer);
        User user = PrefManager.getInstance(this).getCustom(AppConstant.SP_USER, User.class);
        APIUtils.getInstance(this).getUserService().getPin(user.getId())
                .enqueue(new ZCallback<PIN>() {
                    @Override
                    public void onResponse(Call<PIN> call, Response<PIN> response) {
                        if (response.code() == 200) {
                            txtPin.setText(response.body().getValue());
                            render(response.body().getExpiredDate(), lblTimer);
                        }
                    }
                });
    }

    private void render(Date expDate, final TextView lbl) {
        lbl.setText("PIN sudah kadaluarsa");
        if (expDate == null) return;
        long exp = expDate.getTime();
        long now = new Date().getTime();
        long diff = exp - now;
        if (diff < 0) return;
        new CountDownTimer(diff, 1000) {
            public void onTick(long millisUntilFinished) {
                lbl.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                lbl.setText("PIN sudah kadaluarsa");
            }
        }.start();
    }


    public void closePage(View view) {
        finish();
    }
}
