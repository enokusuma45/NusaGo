package id.zcode.android.nusago.component;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import id.zcode.android.nusago.util.MyState;

public class ZActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyState.view = findViewById(android.R.id.content);
    }
}