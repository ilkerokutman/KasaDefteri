package com.performans.io.kasadefteri;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;




public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {

    private Context mContext;
    private List<TaskModel> itemList;

    public TaskListAdapter(Context mContext, List<TaskModel> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyListAdapter.MyViewHolder holder,
                                 final int position) {
        final TaskModel item = itemList.get(position);
        holder.clientName.setText(item.getClientFullName() + " #" + item.getStatusId());
        holder.clientPhone.setText(item.getClientNormalizedPhone());
        holder.vehicleInfo.setText(item.getVehicleInfo());
        Picasso.get()
                .load(item.isOngoingTask() ? R.drawable.icon_task_active : R.drawable.icon_task_idle)
                .into(holder.taskIcon);
        holder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, TaskDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.BKEY_ID, item.getId());
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView clientName, clientPhone, vehicleInfo;
        public RelativeLayout layout;
        ImageView taskIcon;

        public MyViewHolder(View view) {
            super(view);
            layout = view.findViewById(R.id.taskitem_layout);
            clientName = view.findViewById(R.id.taskitem_clientName);
            clientPhone = view.findViewById(R.id.taskitem_clientPhone);
            vehicleInfo = view.findViewById(R.id.taskitem_vehicleInfo);
            taskIcon = view.findViewById(R.id.taskitem_icon);
        }
    }
}
