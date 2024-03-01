package com.example.mymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymanager.databinding.EachrvBinding;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends ListAdapter<Note,RVAdapter.ViewHolder> {
//    NoteRepo nr=new NoteRepo();
    public RVAdapter() {
        super(CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Note> CALLBACK=new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDesc().equals(newItem.getDesc()) &&
                    oldItem.getTime().equals(newItem.getTime());
        }
    };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.eachrv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note=getItem(position);
        holder.binding.timerv.setText(note.getTime());
        holder.binding.titlerv.setText(note.getTitle());
        holder.binding.discrv.setText(note.getDesc());
    }
//    @Override
//    public  int getItemCount(){
//
//    }
    public Note getNote(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        EachrvBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=EachrvBinding.bind(itemView);
        }
    }
}
