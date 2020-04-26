package com.diki.idn.crudmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diki.idn.crudmovie.R;
import com.diki.idn.crudmovie.data.DetailDataMovie;
import com.diki.idn.crudmovie.model.UserItem;

import java.util.List;

//Adapter = menghubungkan data ke view

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context context;
    private List<UserItem> userItems;

    public MainAdapter(Context context, List<UserItem> userItems) {
        this.context = context;
        this.userItems = userItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final UserItem userItem = userItems.get(position);
        holder.title.setText(userItem.getTitle());
        holder.description.setText(userItem.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailDataMovie.class);
                intent.putExtra("title", userItem.getTitle());
                intent.putExtra("description", userItem.getDescription());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_desc);
        }
    }
}
