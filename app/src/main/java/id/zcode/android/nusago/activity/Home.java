package id.zcode.android.nusago.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import id.zcode.android.nusago.component.ZCallback;
import id.zcode.android.nusago.model.User;
import id.zcode.android.nusago.util.APIUtils;
import id.zcode.android.nusago.util.AppConstant;
import id.zcode.android.nusago.util.PrefManager;
import retrofit2.Call;
import retrofit2.Response;


public class Home extends AppCompatActivity {
    private String phone;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initValue();
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initValue();
                refreshLayout.setRefreshing(false);
            }
        });
        Button buttonOpenBottomSheet = findViewById(R.id.ShowBarcode);
        buttonOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBarcode bottomSheet = new ShowBarcode();
                Bundle bundle = new Bundle();
                bundle.putString("phone", phone);
                bottomSheet.setArguments(bundle);
                bottomSheet.show(getSupportFragmentManager(), "au amda");
            }
        });


        Button mButton = findViewById(R.id.History);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, History.class));
            }
        });
    }

    private void initValue() {
        User user = PrefManager.getInstance(Home.this).getCustom(AppConstant.SP_USER, User.class);
        render(user);

        APIUtils.getUserService(this).me().enqueue(new ZCallback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    User user = response.body();
                    render(user);
                    PrefManager.getInstance(Home.this).putCustom(AppConstant.SP_USER, user);
                }
            }
        });
    }

    private void render(User user) {
        phone = user.getPhone();
        TextView name = findViewById(R.id.txtName),
                saldo = findViewById(R.id.txtSaldo);
        if (user.getName() == null || user.getName().isEmpty())
            name.setText(user.getPhone());
        else
            name.setText(user.getName());
        saldo.setText(String.format("Rp %,.0f", user.getSaldo()));

    }

    public void clickProfile(View view) {
        startActivity(new Intent(Home.this, Profile.class));
    }

    public void createPin(View view) {
        startActivity(new Intent(Home.this, BuatPin.class));
    }

}
