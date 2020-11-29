package com.example.studyplanner.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyplanner.R;
import com.example.studyplanner.models.ToDoClass;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    private ArrayList<ToDoClass> mExampleList;

    public ArrayList<ToDoClass> getmExampleList() {
        return mExampleList;
    }

    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_subject;
        public TextView tv_date;
        public CheckBox ch_done;
        public ConstraintLayout cl_todoItem;

        public ToDoViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_Title);
            tv_subject = itemView.findViewById(R.id.tv_Subject);
            tv_date = itemView.findViewById(R.id.tv_Date);
            ch_done = itemView.findViewById(R.id.checkBox_Done);
            cl_todoItem = itemView.findViewById(R.id.cl_todoItem);
        }
    }

    public ToDoAdapter(ArrayList<ToDoClass> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todolist_item, parent, false);
        ToDoViewHolder evh = new ToDoViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {
        ToDoClass currentItem = mExampleList.get(position);
        holder.tv_title.setText(mExampleList.get(position).getTitle());
        holder.tv_subject.setText(mExampleList.get(position).getSubject());
        holder.tv_date.setText(mExampleList.get(position).getDate());
        if(mExampleList.get(position).isDone()){
            holder.ch_done.setChecked(true);

            holder.tv_title.setPaintFlags(holder.tv_title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tv_subject.setPaintFlags(holder.tv_subject.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tv_date.setPaintFlags(holder.tv_date.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.cl_todoItem.setBackgroundResource(R.color.gray);
        } else {
            holder.ch_done.setChecked(false);

            holder.tv_title.setPaintFlags(holder.tv_title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.tv_subject.setPaintFlags(holder.tv_subject.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.tv_date.setPaintFlags(holder.tv_date.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

            holder.cl_todoItem.setBackgroundResource(R.color.white);
        }


        holder.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExampleList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.ch_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    mExampleList.get(position).setDone(true);
                    holder.tv_title.setPaintFlags(holder.tv_title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.tv_subject.setPaintFlags(holder.tv_subject.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.tv_date.setPaintFlags(holder.tv_date.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.cl_todoItem.setBackgroundResource(R.color.gray);

                    Toast.makeText(view.getContext(), "true", LENGTH_SHORT).show();
                } else {
                    mExampleList.get(position).setDone(false);

                    holder.tv_title.setPaintFlags(holder.tv_title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    holder.tv_subject.setPaintFlags(holder.tv_subject.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    holder.tv_date.setPaintFlags(holder.tv_date.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

                    holder.cl_todoItem.setBackgroundResource(R.color.white);

                    Toast.makeText(view.getContext(), "false", LENGTH_SHORT).show();

                }
            }
        });
        {

        }


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}