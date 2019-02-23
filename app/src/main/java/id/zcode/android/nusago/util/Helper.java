package id.zcode.android.nusago.util;

import android.support.design.widget.Snackbar;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Helper {
    public static void showMessage(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void showMessage(String message) {
        showMessage(MyState.view, message);
    }

    public static Map<String, String> getThreeMonths(Date date){
        Map<String, String> result = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endDate = sdf.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -3);
        String startDate = sdf.format(c.getTime());
        result.put("startDate", startDate);
        result.put("endDate", endDate);
        return result;
    }
}
