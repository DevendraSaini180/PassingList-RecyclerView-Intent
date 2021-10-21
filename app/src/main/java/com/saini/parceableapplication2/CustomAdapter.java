package com.saini.parceableapplication2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    Context mContext;
    List<PhotoModel> mList;

    public CustomAdapter(Context context, List<PhotoModel> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_photo_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhotoModel photoModel = mList.get(position);
        String idText = "Id: " + photoModel.getId();
        holder.id.setText(idText);
        String authorText = "Author: " + photoModel.getAuthor();
        holder.author.setText(authorText);
        String urlText = "Url: " + photoModel.getUrl();
        holder.url.setText(urlText);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Data passed to received activity", Toast.LENGTH_SHORT).show();
                ArrayList<String> list = new ArrayList<>();
                list.add(photoModel.getId());
                list.add(photoModel.getAuthor());
                list.add(photoModel.getUrl());
                Intent intent = new Intent(holder.itemView.getContext(), ReceivingActivity.class);
                intent.putExtra("ApiData", list);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, author, url;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout_item);
            id = itemView.findViewById(R.id.textViewId);
            author = itemView.findViewById(R.id.textViewAuthor);
            url = itemView.findViewById(R.id.textViewUrl);
        }
    }

}
