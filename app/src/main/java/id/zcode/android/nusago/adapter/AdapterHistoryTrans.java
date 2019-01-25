package id.zcode.android.nusago.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import id.zcode.android.nusago.R;
import id.zcode.android.nusago.model.HistoryTrans;

import java.util.List;


public class AdapterHistoryTrans extends RecyclerView.Adapter<AdapterHistoryTrans.HistoryTransHolder> {

    private List<HistoryTrans> dataList;

    public AdapterHistoryTrans(List<HistoryTrans> dataList) {
        this.dataList = dataList;
    }

    @Override
    public HistoryTransHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_history, parent, false);
        return new HistoryTransHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryTransHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNpm.setText(dataList.get(position).getTransDate());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class HistoryTransHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtNpm;

        public HistoryTransHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.tvNamaToko);
            txtNpm = (TextView) itemView.findViewById(R.id.tvDateTrans);
        }
    }
}
