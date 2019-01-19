package id.zcode.android.nusago.util;

import android.support.design.widget.Snackbar;
import android.view.View;

public class Helper {
    public static void showMessage(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void showMessage(String message) {
        showMessage(MyState.view, message);
    }

}
