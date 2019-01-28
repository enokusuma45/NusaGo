package id.zcode.android.nusago.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class ShowBarcode extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_sheet_barcode, container, true);
        initData(v);
        return v;
    }

    private int getWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
        return displayMetrics.widthPixels;
    }

    private void initData(View v) {
        Bundle args = getArguments();
        String text = args.getString("phone", "");
        ImageView iv = v.findViewById(R.id.imgBarcode);
        TextView txt = v.findViewById(R.id.txtPhone);
        txt.setText(text);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            int width = (int) (getWidth() * 0.8);
            int height = (int) (width * 0.3);
//            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, width, width);
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.CODE_128, width, height);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            iv.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
