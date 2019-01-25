package id.zcode.android.nusago;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import id.zcode.android.nusago.adapter.PurchaseDetailAdapter;
import id.zcode.android.nusago.model.SalesOrder;
import id.zcode.android.nusago.model.SalesOrderDetail;

import java.text.SimpleDateFormat;


public class PurchaseDetail extends BottomSheetDialogFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SalesOrder salesOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_totalharga_popup, container, true);
        mRecyclerView = v.findViewById(R.id.rvProducts);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        initData(v);
        return v;
    }

    private void initData(View v) {
        Bundle args = getArguments();
        String temp = args.getString("so", "");
        salesOrder = new Gson().fromJson(temp, SalesOrder.class);
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

        TextView txtStoreName, txtStoreAddress, txtDate, txtTotalPrice;
        txtStoreName = v.findViewById(R.id.txtStoreName);
        txtStoreAddress = v.findViewById(R.id.txtStoreAddress);
        txtDate = v.findViewById(R.id.txtDate);
        txtTotalPrice = v.findViewById(R.id.txtTotalPrice);

        txtStoreName.setText(salesOrder.getStore().getName());
        txtStoreAddress.setText(salesOrder.getStore().getAddress());
        txtDate.setText(sdf.format(salesOrder.getDate()));
        double total = 0;
        for(SalesOrderDetail sod : salesOrder.getSalesOrderDetails()){
            total += sod.getPrice() * sod.getQuantity();
        }
        txtTotalPrice.setText(String.format("Rp %,.0f", total));

        mRecyclerView.setAdapter(new PurchaseDetailAdapter(salesOrder.getSalesOrderDetails()));
    }
}
