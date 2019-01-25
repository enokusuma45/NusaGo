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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initValue();

        Button buttonOpenBottomSheet = findViewById(R.id.ShowBarcode);
        buttonOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show_barcode bottomSheet = new Show_barcode();
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
        User user = PrefManager.getInstance(Home.this).getCustom(AppConstant.SP_USER, User.class);
        name.setText(user.getName());
    }


}
