package com.example.planningpokerproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerproject.interfaces.OnItemClickListener;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleViewHolder> {

    private List<RecyclerItem> mRecyclerItemArrayList;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder{

        public TextView mTaskDescription,mStartTime,mEndTime;

        public RecycleViewHolder(@NonNull View itemView,  final OnItemClickListener listener) {
            super(itemView);
            mTaskDescription = itemView.findViewById(R.id.task_descriptionTextView);
            mStartTime = itemView.findViewById(R.id.start_timeTextView);
            mEndTime = itemView.findViewById(R.id.end_timeTextView);

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
        }
    }

    public RecyclerAdapter(List<RecyclerItem> recyclerItemArrayList){
        this.mRecyclerItemArrayList = recyclerItemArrayList;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        RecycleViewHolder rvh = new RecycleViewHolder(view,mOnItemClickListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        RecyclerItem currentItem = mRecyclerItemArrayList.get(position);

        holder.mTaskDescription.setText(currentItem.getTask_description());
        holder.mStartTime.setText(currentItem.getStart_time());
        holder.mEndTime.setText(String.valueOf(currentItem.getEnd_time()));
    }

    @Override
    public int getItemCount() {
        return mRecyclerItemArrayList.size();
    }
}
