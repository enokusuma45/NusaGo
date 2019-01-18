package id.zcode.android.nusago.service;

import id.zcode.android.nusago.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.Map;

public interface UserService {
    @POST("register")
    Call<User> register(@Body User user);

    @POST("activation")
    Call<Void> activation(@Body Map map);

    @PUT("resend-verification-code/{id}")
    Call<Void> resendVerificationCode(@Path("id") String userId);
}
