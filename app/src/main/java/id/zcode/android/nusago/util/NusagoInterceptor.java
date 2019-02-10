package id.zcode.android.nusago.util;


import android.content.Context;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

public class NusagoInterceptor implements Interceptor {
    private Context context;

    public NusagoInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = PrefManager.getInstance(context)
                .getString(AppConstant.SP_TOKEN, "");
        Request request = chain.request();
        Request modifiedRequest = request.newBuilder()
                .addHeader("token", token)
                .build();
        Response response = chain.proceed(modifiedRequest);
        if (response.code() != 200) handleError(response);
        return response;
    }

    private void handleError(Response response) {
        try {
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            String responseBodyString = buffer.clone().readString(Charset.forName("UTF-8"));
            JSONObject jo = new JSONObject(responseBodyString);
            Helper.showMessage(jo.getString("message"));
        } catch (Exception e) {
            Helper.showMessage("Gagal menampilkan error message");
            e.printStackTrace();
        }
    }
}

