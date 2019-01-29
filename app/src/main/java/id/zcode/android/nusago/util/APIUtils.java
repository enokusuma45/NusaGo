package id.zcode.android.nusago.util;

import android.content.Context;
import id.zcode.android.nusago.service.AuthService;
import id.zcode.android.nusago.service.SalesOrderService;
import id.zcode.android.nusago.service.UserService;

public class APIUtils {

    public static AuthService getAuthService(Context context) {
        String AUTH_URL = AppConstant.BASE_URL + "/auth/";
        return RetrofitClient.getClient(context, AUTH_URL).create(AuthService.class);
    }

    public static UserService getUserService(Context context) {
        String USER_URL = AppConstant.BASE_URL + "/user/";
        return RetrofitClient.getClient(context, USER_URL).create(UserService.class);
    }

    public static SalesOrderService getSalesOrderService(Context context) {
        String SALES_ORDER_URL = AppConstant.BASE_URL + "/sales-order/";
        return RetrofitClient.getClient(context, SALES_ORDER_URL).create(SalesOrderService.class);
    }
}
