

package com.example.arob.nusago;

        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.design.widget.BottomSheetDialogFragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

public class Totalharga_popup extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_totalharga_popup, container, true);

        return v;



    }
}
