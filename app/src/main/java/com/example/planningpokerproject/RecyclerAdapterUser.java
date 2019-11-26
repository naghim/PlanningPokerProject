package com.example.planningpokerproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokerproject.Model.User;

import java.util.List;

public class RecyclerAdapterUser extends RecyclerView.Adapter<RecyclerAdapterUser.RecycleViewHolderUser> {

    private List<User> mRecyclerItemArrayList;

    public static class RecycleViewHolderUser extends RecyclerView.ViewHolder{

        public TextView mUserNameTextView,mValueTextView;

        public RecycleViewHolderUser(@NonNull View itemView) {
            super(itemView);
            mUserNameTextView = itemView.findViewById(R.id.userNameTextView);
            mValueTextView = itemView.findViewById(R.id.valueTextView);
        }
    }

    public RecyclerAdapterUser(List<User> recyclerItemArrayList){
        this.mRecyclerItemArrayList = recyclerItemArrayList;
    }

    @NonNull
    @Override
    public RecyclerAdapterUser.RecycleViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_user, parent, false);
        RecyclerAdapterUser.RecycleViewHolderUser rvh = new RecyclerAdapterUser.RecycleViewHolderUser(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterUser.RecycleViewHolderUser holder, int position) {
        User currentItem = mRecyclerItemArrayList.get(position);
        if (currentItem !=null){
            holder.mUserNameTextView.setText(currentItem.getName());
            holder.mValueTextView.setText(String.valueOf(currentItem.getValue()));
        }
    }

    @Override
    public int getItemCount() {
        return mRecyclerItemArrayList.size();
    }
}