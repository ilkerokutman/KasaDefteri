package com.performans.io.kasadefteri;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;




public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {

    private Context mContext;
    private List<ExpenseModel> itemList;

    public MyListAdapter(Context mContext, List<ExpenseModel> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyListAdapter.MyViewHolder holder,
                                 final int position) {
        final ExpenseModel item = itemList.get(position);

        holder.itemdesc.setText(item.getDescription());
        holder.itemamount.setText(String.valueOf(item.getAmount()));
        holder.itemdate.setText(item.getDate());
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Dikkat");
                builder.setMessage("bunu silmek istediğinizden emin misiniz?");
                builder.setNegativeButton("HAYIR", null);
                builder.setPositiveButton("SİL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        DatabaseHelper db = new DatabaseHelper(mContext);
                        db.deleteRecord(item.getId());
                        ((HomeActivity)mContext).onResume();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemdate, itemdesc, itemamount;
        ImageView itemicon;
        RelativeLayout layout;

        public MyViewHolder(View view) {
            super(view);
            itemdate = view.findViewById(R.id.item_date);
            itemamount = view.findViewById(R.id.item_amount);
            itemdesc = view.findViewById(R.id.item_desc);
            itemicon = view.findViewById(R.id.item_icon);
            layout = view.findViewById(R.id.item_layout);
        }
    }
}
