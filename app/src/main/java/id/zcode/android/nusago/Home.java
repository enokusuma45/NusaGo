package id.zcode.android.nusago;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import id.zcode.android.nusago.model.User;
import id.zcode.android.nusago.util.AppConstant;
import id.zcode.android.nusago.util.PrefManager;


public class Home extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initValue();
        Button buttonOpenBottomSheet = findViewById(R.id.ShowBarcode);
        buttonOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBarcode bottomSheet = new ShowBarcode();
                Bundle bundle = new Bundle();
                bundle.putString("phone", user.getPhone());
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
        TextView name = findViewById(R.id.txtName),
                saldo = findViewById(R.id.txtSaldo);
        user = PrefManager.getInstance(Home.this).getCustom(AppConstant.SP_USER, User.class);
        if (user.getName() == null || user.getName().isEmpty())
            name.setText(user.getPhone());
        else
            name.setText(user.getName());
        saldo.setText(String.format("Rp %,.0f", user.getSaldo()));
    }


}
