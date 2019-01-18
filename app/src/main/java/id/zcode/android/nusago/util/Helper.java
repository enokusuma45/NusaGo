package id.zcode.android.nusago.util;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

public class Helper {
    public static void showMessage(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void showMessage(Activity activity, String message) {
        final View view = activity.findViewById(android.R.id.content);
        showMessage(view, message);
    }
}
