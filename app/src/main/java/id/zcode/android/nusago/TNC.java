package id.zcode.android.nusago;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import id.zcode.android.nusago.model.User;
import id.zcode.android.nusago.util.APIUtils;
import id.zcode.android.nusago.util.Helper;
import id.zcode.android.nusago.util.PrefManager;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TNC extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_tnc, container, false);
        Button btnOtp = v.findViewById(R.id.Otp);
        btnOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        return v;
    }

    private void register() {
        final View view = getView();
        final User user = PrefManager.getInstance(getActivity()).getCustom("user", User.class);
        APIUtils.getInstance(getActivity()).getUserService()
                .register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() != 200) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Helper.showMessage(view, jObjError.getString("message"));
                    } catch (Exception e) {
                        Helper.showMessage(view, "Gagal menampilkan error message");
                        e.printStackTrace();
                    }
                } else {
                    Helper.showMessage(view, "Pendaftaran user berhasil");
                    user.setId(response.body().getId());
                    PrefManager.getInstance(getActivity()).putCustom("user", user);
                    startActivity(new Intent(getActivity(), Otp.class));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Helper.showMessage(view, t.getMessage());
            }
        });
    }


}
