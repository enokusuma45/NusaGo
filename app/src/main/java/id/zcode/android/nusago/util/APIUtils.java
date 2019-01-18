package id.zcode.android.nusago.util;

import android.content.Context;
import id.zcode.android.nusago.service.UserService;

public class APIUtils {
    private static final String USER_URL = AppConstant.BASE_URL + "/user/";
    private static APIUtils instance;
    private Context context;

    public static APIUtils getInstance(Context context) {
        if (instance == null) {
            instance = new APIUtils();
            instance.context = context;
        }
        return instance;
    }

    public UserService getUserService() {
        return RetrofitClient.getClient(context, USER_URL).create(UserService.class);
    }
}
