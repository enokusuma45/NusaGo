package id.zcode.android.nusago;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import butterknife.BindView;
import com.google.gson.Gson;
import id.zcode.android.nusago.adapter.AdapterHistoryTrans;
import id.zcode.android.nusago.component.ZCallback;
import id.zcode.android.nusago.model.HistoryTrans;
import id.zcode.android.nusago.model.Pageable;
import id.zcode.android.nusago.model.SalesOrder;
import id.zcode.android.nusago.util.APIUtils;
import id.zcode.android.nusago.util.RecyclerItemClickListener;
import retrofit2.Call;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class History extends AppCompatActivity {

    @BindView(R.id.rvHistory)
    RecyclerView rvHistory;
    Context mContext;
    private List<SalesOrder> salesOrders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rvHistory = findViewById(R.id.rvHistory);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(History.this);
        rvHistory.setLayoutManager(layoutManager);
        initData();
        ImageButton close = findViewById(R.id.bt_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(History.this, Home.class));
                finish();
            }
        });
    }


    void initData() {
        Call<Pageable<SalesOrder>> salesOrderCall = APIUtils.getInstance(this).getSalesOrderService().get(0, 20);
        salesOrderCall.enqueue(new ZCallback<Pageable<SalesOrder>>() {
            @Override
            public void onResponse(Call<Pageable<SalesOrder>> call, Response<Pageable<SalesOrder>> response) {
                if (response.code() == 200) {
                    List<HistoryTrans> transactions = new ArrayList<>();
                    Pageable<SalesOrder> pageable = response.body();
                    salesOrders = pageable.getContent();
                    for (SalesOrder so : salesOrders) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy HH:mm");
                        transactions.add(
                                new HistoryTrans(
                                        so.getStore().getName(),
                                        sdf.format(so.getDate())));
                    }
                    rvHistory.setAdapter(new AdapterHistoryTrans(transactions));
                    rvHistory.addOnItemTouchListener(
                            new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    SalesOrder salesOrder = salesOrders.get(position);
                                    PurchaseDetail bottomSheet = new PurchaseDetail();
                                    Bundle args = new Bundle();
                                    args.putString("so", new Gson().toJson(salesOrder));
                                    bottomSheet.setArguments(args);
                                    bottomSheet.show(getSupportFragmentManager(), "au amda");
                                }
                            }));
                }
            }
        });
    }

}
