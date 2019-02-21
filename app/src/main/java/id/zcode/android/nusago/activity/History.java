package id.zcode.android.nusago.activity;

import android.content.Context;
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
import id.zcode.android.nusago.model.Container2;
import id.zcode.android.nusago.model.HistoryTrans;
import id.zcode.android.nusago.model.SalesOrder;
import id.zcode.android.nusago.model.User;
import id.zcode.android.nusago.util.APIUtils;
import id.zcode.android.nusago.util.AppConstant;
import id.zcode.android.nusago.util.PrefManager;
import id.zcode.android.nusago.util.RecyclerItemClickListener;
import retrofit2.Call;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
                finish();
            }
        });
    }


    void initData() {
        User user = PrefManager.getInstance(this).getCustom(AppConstant.SP_USER, User.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String endDate = sdf.format(now);
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.MONTH, -3);
//        c.set(Calendar.DATE, 1);
        String startDate = sdf.format(c.getTime());

        final Call<Container2<SalesOrder>> salesOrderCall = APIUtils.getSalesOrderService(this)
                .get(user.getId().trim(), startDate, endDate, 0, 100);
        salesOrderCall.enqueue(new ZCallback<Container2<SalesOrder>>() {
            @Override
            public void onResponse(Call<Container2<SalesOrder>> call, Response<Container2<SalesOrder>> response) {
                if (response.code() == 200) {
                    List<HistoryTrans> transactions = new ArrayList<>();
                    Container2<SalesOrder> pageable = response.body();
                    List<SalesOrder> temp = pageable.getData();
                    salesOrders = new ArrayList<>();
                    for (SalesOrder so : temp) {
                        if (so.getTotal() > 0) {
                            salesOrders.add(so);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
                            transactions.add(
                                    new HistoryTrans(
                                            so.getStore(),
                                            sdf.format(so.getDate())));
                        }
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
