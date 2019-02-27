package id.zcode.android.nusago.service;

import id.zcode.android.nusago.model.Container2;
import id.zcode.android.nusago.model.Pageable;
import id.zcode.android.nusago.model.SalesOrder;
import id.zcode.android.nusago.model.SalesOrderDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SalesOrderService {
    @GET(".")
    Call<Pageable<SalesOrder>> get(@Query("page") int page, @Query("size") int size);

    @GET("view/bydate/transaksi/")
    Call<Container2<SalesOrder>> get(@Query("idmember") String memberId,
                                     @Query("date") String startDate,
                                     @Query("date2") String endDate,
                                     @Query("page") int page,
                                     @Query("size") int size);

    @GET("view/bydate/dettransaksi/")
    Call<Container2<SalesOrderDetail>> getDetail(@Query("idtransaksi") String transactionId,
                                                 @Query("page") int page,
                                                 @Query("size") int size);
}
