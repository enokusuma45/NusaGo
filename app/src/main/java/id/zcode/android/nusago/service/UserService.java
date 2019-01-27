package id.zcode.android.nusago.service;

import id.zcode.android.nusago.model.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("me")
    Call<User> me();
}
