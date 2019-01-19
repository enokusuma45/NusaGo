package id.zcode.android.nusago.component;

import id.zcode.android.nusago.util.Helper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ZCallback<T> implements Callback<T> {
    @Override
    public abstract void onResponse(Call<T> call, Response<T> response);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Helper.showMessage(t.getMessage());
    }
}
