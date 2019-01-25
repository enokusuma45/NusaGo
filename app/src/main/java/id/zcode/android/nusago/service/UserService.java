package id.zcode.android.nusago.service;

import id.zcode.android.nusago.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.Map;

public interface UserService {
    @POST("register")
    Call<User> register(@Body User user);

    @POST("activation")
    Call<String> activation(@Body Map map);

    @POST("resend-verification-code/{id}")
    Call<Void> resendVerificationCode(@Path("id") String userId);

    @POST("check-phone/{phone}")
    Call<User> checkPhone(@Path("phone") String phone);
}
