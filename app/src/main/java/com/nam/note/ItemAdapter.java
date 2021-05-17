package com.nam.note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    List<Item> list;
    Context context;

    public ItemAdapter(List<Item> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Item item=list.get(position);
       holder.title.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.tv1);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition(), view);
        }
    }
    private ClickListener listener;

    public void setItemClickListener(ClickListener clickListener) {
        listener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
