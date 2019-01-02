package com.example.arob.nusago;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;




public class Register extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button buttonOpenBottomSheet = findViewById(R.id.selanjutnya);
        buttonOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tnc bottomSheet = new tnc();
                bottomSheet.show(getSupportFragmentManager(), "au amda");
            }
        });


//        Button mButton = (Button) findViewById(R.id.selanjutnya);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Register.this, Otp.class));
//            }
//        });
    }
}





