package id.zcode.android.nusago.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class NusagoInterceptor implements Interceptor {
    private SharedPreferences sharedPreferences;

    public NusagoInterceptor(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = sharedPreferences.getString(AppConstant.SP_TOKEN, "");
        Request request = chain.request();
        Request modifiedRequest = request.newBuilder()
                .addHeader("token", token)
                .build();
        return chain.proceed(modifiedRequest);
    }
}

