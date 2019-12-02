package com.example.planningpokerproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerproject.interfaces.OnItemClickListener;
import com.example.planningpokerproject.interfaces.ItemLongClickListener;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleViewHolder> {

    private List<RecyclerItem> mRecyclerItemArrayList;

    private OnItemClickListener mOnItemClickListener;
    private ItemLongClickListener itemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public void setItemLongClickListener(ItemLongClickListener listener){
        this.itemLongClickListener = listener;
    }


    public static class RecycleViewHolder extends RecyclerView.ViewHolder{

        public TextView mTaskDescription,mStartTime,mEndTime;
        public CheckBox mIsActiveCheckBox;

        public RecycleViewHolder(@NonNull View itemView, final OnItemClickListener listener, final ItemLongClickListener longClickListener ) {
            super(itemView);
            mTaskDescription = itemView.findViewById(R.id.task_descriptionTextView);
            mStartTime = itemView.findViewById(R.id.start_timeTextView);
            mEndTime = itemView.findViewById(R.id.end_timeTextView);
            mIsActiveCheckBox = itemView.findViewById(R.id.isActiveCheckBox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(v,position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if ( longClickListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            longClickListener.onItemLongClick(v,position);
                            return true;
                        }
                    }

                    return false;
                }
            });

        }
    }

    public RecyclerAdapter(List<RecyclerItem> recyclerItemArrayList){
        this.mRecyclerItemArrayList = recyclerItemArrayList;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        RecycleViewHolder rvh = new RecycleViewHolder(view,mOnItemClickListener, itemLongClickListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        RecyclerItem currentItem = mRecyclerItemArrayList.get(position);

        holder.mTaskDescription.setText(currentItem.getTask_description());
        holder.mStartTime.setText(currentItem.getStart_time());
        holder.mEndTime.setText(String.valueOf(currentItem.getEnd_time()));
        holder.mIsActiveCheckBox.setChecked(currentItem.getIsActive());
    }

    @Override
    public int getItemCount() {
        return mRecyclerItemArrayList.size();
    }
}
