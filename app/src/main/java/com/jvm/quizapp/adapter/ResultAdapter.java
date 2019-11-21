package com.jvm.quizapp.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jvm.quizapp.R;
import com.jvm.quizapp.model.Result;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {
    List<Result> mResultList;
    Activity mContext;
    private OnItemClickListener onItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView anwser, userChoice, question;
        ImageView userImg;

        public MyViewHolder(View view) {
            super(view);
            anwser = view.findViewById(R.id.answer);
            userChoice = view.findViewById(R.id.userAnswer);
            question = view.findViewById(R.id.question);

        }
        void bind(final int position, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(mResultList.get(position),position);
                }
            });
        }

    }


    public ResultAdapter(List<Result> mResultList) {
        this.mResultList = mResultList;
        //this.mContext = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Result item = mResultList.get(position);

        holder.anwser.setText(String.format("   Answer : %s",item.getAnswer()));
        holder.userChoice.setText(String.format("   Your choice : %s",item.getUserChoice()));
        holder.question.setText(String.format("%s . %s",position+1,item.getQuestion()));
        holder.bind(position, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Result item, int position);
    }

    public void setActionModeReceiver(OnItemClickListener receiver) {
        this.onItemClickListener = receiver;
    }
}
