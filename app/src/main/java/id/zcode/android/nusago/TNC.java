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
import id.zcode.android.nusago.component.ZCallback;
import id.zcode.android.nusago.model.User;
import id.zcode.android.nusago.util.APIUtils;
import id.zcode.android.nusago.util.AppConstant;
import id.zcode.android.nusago.util.PrefManager;
import retrofit2.Call;
import retrofit2.Response;


public class TNC extends BottomSheetDialogFragment {
    String phone = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_tnc, container, false);
        Bundle args = getArguments();
        phone = args.getString("phone", "");

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
        APIUtils.getInstance(getActivity()).getAuthService()
                .register(phone).enqueue(new ZCallback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    User user = response.body();
                    PrefManager.getInstance(getActivity()).putCustom(AppConstant.SP_USER, user);
                    startActivity(new Intent(getActivity(), Otp.class));
                } else {
                    dismiss();
                }
            }
        });
    }


}
