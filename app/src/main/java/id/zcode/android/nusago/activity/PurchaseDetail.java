package id.zcode.android.nusago.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.gson.Gson;
import id.zcode.android.nusago.adapter.PurchaseDetailAdapter;
import id.zcode.android.nusago.component.ZCallback;
import id.zcode.android.nusago.model.Container2;
import id.zcode.android.nusago.model.SalesOrder;
import id.zcode.android.nusago.model.SalesOrderDetail;
import id.zcode.android.nusago.util.APIUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.List;


public class PurchaseDetail extends BottomSheetDialogFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SalesOrder salesOrder;
    private ScrollView scrollView;
    private TextView loadingText;
    private LinearLayout totalHargaContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_totalharga_popup, container, true);
        scrollView = v.findViewById(R.id.scrollViewProducts);
        loadingText = v.findViewById(R.id.loadingProducts);
        mRecyclerView = v.findViewById(R.id.rvProducts);
        totalHargaContainer = v.findViewById(R.id.totalPriceContainer);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        initData(v);
        return v;
    }

    private void showLoading(boolean isLoading) {
        loadingText.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        scrollView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        totalHargaContainer.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }

    private void initData(View v) {
        showLoading(true);
        Bundle args = getArguments();
        String temp = args.getString("so", "");
        salesOrder = new Gson().fromJson(temp, SalesOrder.class);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

        TextView txtStoreName, txtStoreAddress, txtDate, txtTotalPrice;
        txtStoreName = v.findViewById(R.id.txtStoreName);
        txtStoreAddress = v.findViewById(R.id.txtStoreAddress);
        txtDate = v.findViewById(R.id.txtDate);
        txtTotalPrice = v.findViewById(R.id.txtTotalPrice);

        txtStoreName.setText(salesOrder.getStore());
        txtStoreAddress.setText("~");
        txtDate.setText(sdf.format(salesOrder.getDate()));
        double total = 0;
        for (SalesOrderDetail sod : salesOrder.getSalesOrderDetails()) {
            total += sod.getPrice() * sod.getQuantity();
        }
        txtTotalPrice.setText(String.format("Rp %,.0f", salesOrder.getTotal()));

        final Call<Container2<SalesOrderDetail>> salesOrderDetailCall =
                APIUtils.getSalesOrderService(getActivity())
                        .getDetail(salesOrder.getCode(), 0, 100);

        salesOrderDetailCall.enqueue(new ZCallback<Container2<SalesOrderDetail>>() {
            @Override
            public void onResponse(Call<Container2<SalesOrderDetail>> call, Response<Container2<SalesOrderDetail>> response) {
                if (response.code() == 200) {
                    Container2<SalesOrderDetail> x = response.body();
                    if (x.getStatus().equals("success")) {
                        List<SalesOrderDetail> salesOrderDetails = x.getData();
                        mRecyclerView.setAdapter(new PurchaseDetailAdapter(salesOrderDetails));
                        showLoading(false);
                    } else {
                        loadingText.setText("tidak dapat mengambail detail produk");
                    }
                }
            }
        });

    }
}
