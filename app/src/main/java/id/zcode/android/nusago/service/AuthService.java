package id.zcode.android.nusago.service;

import id.zcode.android.nusago.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.Map;

public interface AuthService {
    @POST("register/{phone}")
    Call<User> register(@Path("phone") String phone);

    @POST("activation")
    Call<String> activation(@Body Map map);

    @GET("resend-code/{userId}")
    Call<Void> resendVerificationCode(@Path("userId") String userId);

    @POST("check-phone/{phone}")
    Call<User> checkPhone(@Path("phone") String phone);
}
