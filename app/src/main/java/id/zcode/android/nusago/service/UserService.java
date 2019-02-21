package id.zcode.android.nusago.service;

import id.zcode.android.nusago.model.Container;
import id.zcode.android.nusago.model.PIN;
import id.zcode.android.nusago.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("member/{id}")
    Call<Container<User>> getUser(@Path("id") String id);

    @GET("pin")
    Call<PIN> getPin();

    @GET("me")
    Call<User> me();
}
