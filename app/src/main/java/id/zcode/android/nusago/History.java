package id.zcode.android.nusago;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;


import id.zcode.android.nusago.adapter.AdapterHistoryTrans;
import id.zcode.android.nusago.model.HistoryTrans;
import id.zcode.android.nusago.util.RecyclerItemClickListener;
import android.support.v7.widget.LinearLayoutManager;

import butterknife.BindView;


public class History extends AppCompatActivity {

    @BindView(R.id.rvHistory)
    RecyclerView rvHistory;

    private RecyclerView recyclerView;
    private AdapterHistoryTrans adapter;
    Context mContext;
    private ArrayList<HistoryTrans> HistoryTransArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



        rvHistory = (RecyclerView) findViewById(R.id.rvHistory);
        adapter = new AdapterHistoryTrans(HistoryTransArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(History.this);
        rvHistory.setLayoutManager(layoutManager);
        rvHistory.setAdapter(adapter);
        addData();
        ImageButton close = (ImageButton) findViewById(R.id.bt_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(History.this, Home.class));
            }
        });
    }


    void addData(){
        HistoryTransArrayList = new ArrayList<>();
        HistoryTransArrayList.add(new HistoryTrans("Toko Berkah abadi", "Senin 31 Desember 2018, 10:45"));
        HistoryTransArrayList.add(new HistoryTrans("Toko 212", "Selasa 1 Januari 2019, 10:45"));
        HistoryTransArrayList.add(new HistoryTrans("Toko Cahaya Kolbu", "Rabu 2 Januari 2019, 10:45"));
        HistoryTransArrayList.add(new HistoryTrans("Toko Sinar Mas", "Kamis 3 Januari 2019, 10:45"));

        rvHistory.setAdapter(new AdapterHistoryTrans(HistoryTransArrayList));
//       AdapterHistoryTrans.notifyDataSetChanged();
        initDataIntent(HistoryTransArrayList);
    }

    private void initDataIntent(ArrayList<HistoryTrans> historyTransArrayList){
        rvHistory.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Totalharga_popup bottomSheet = new Totalharga_popup();
                        bottomSheet.show(getSupportFragmentManager(), "au amda");
                    }
                }));
    }

}
