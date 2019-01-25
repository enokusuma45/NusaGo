package id.zcode.android.nusago.adapter;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import id.zcode.android.nusago.R;
import id.zcode.android.nusago.model.SalesOrderDetail;

import java.util.List;


public class PurchaseDetailAdapter extends RecyclerView.Adapter<PurchaseDetailAdapter.Holder> {

    private List<SalesOrderDetail> dataList;

    public PurchaseDetailAdapter(List<SalesOrderDetail> dataList) {
        this.dataList = dataList;
        dataList.add(0, new SalesOrderDetail());
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_purchase, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (position == 0) {
            holder.txtNama.setText("Nama");
            holder.txtQuantity.setText("Qty");
            holder.txtPrice.setText("Harga");
            holder.txtNama.setTypeface(null, Typeface.BOLD);
            holder.txtPrice.setTypeface(null, Typeface.BOLD);
            holder.txtQuantity.setTypeface(null, Typeface.BOLD);
            return;
        }
        SalesOrderDetail sod = dataList.get(position);
        holder.txtNama.setText(sod.getProductName());
        holder.txtQuantity.setText(String.format("%,d", sod.getQuantity()));
        holder.txtPrice.setText(String.format("%,.0f", sod.getPrice()));
        holder.txtNama.setTypeface(null, Typeface.NORMAL);
        holder.txtPrice.setTypeface(null, Typeface.NORMAL);
        holder.txtQuantity.setTypeface(null, Typeface.NORMAL);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtQuantity, txtPrice;

        public Holder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtProductName);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
