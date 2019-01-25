package id.zcode.android.nusago.service;

import id.zcode.android.nusago.model.Pageable;
import id.zcode.android.nusago.model.SalesOrder;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesOrderService {
    @GET(".")
    Call<Pageable<SalesOrder>> get(@Query("page") int page, @Query("size") int size);
}
