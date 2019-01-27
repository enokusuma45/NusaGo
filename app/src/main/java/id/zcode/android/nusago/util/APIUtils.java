package id.zcode.android.nusago.util;

import android.content.Context;
import id.zcode.android.nusago.service.AuthService;
import id.zcode.android.nusago.service.SalesOrderService;
import id.zcode.android.nusago.service.UserService;

public class APIUtils {
    private static final String AUTH_URL = AppConstant.BASE_URL + "/auth/";
    private static final String USER_URL = AppConstant.BASE_URL + "/user/";
    private static final String SALES_ORDER_URL = AppConstant.BASE_URL + "/sales-order/";
    private static APIUtils instance;
    private Context context;

    public static APIUtils getInstance(Context context) {
        if (instance == null) {
            instance = new APIUtils();
            instance.context = context;
        }
        return instance;
    }

    public AuthService getAuthService() {
        return RetrofitClient.getClient(context, AUTH_URL).create(AuthService.class);
    }

    public UserService getUserService() {
        return RetrofitClient.getClient(context, USER_URL).create(UserService.class);
    }

    public SalesOrderService getSalesOrderService() {
        return RetrofitClient.getClient(context, SALES_ORDER_URL).create(SalesOrderService.class);
    }
}
