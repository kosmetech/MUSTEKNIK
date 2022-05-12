package com.project.musteknik.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.musteknik.R;
import com.project.musteknik.model.ticket.DataItem;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {
    List<DataItem> mList;
    Context context;
    private static ClickListener clickListener;
    private LayoutInflater mInflater;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // data is passed into the constructor
    public TicketAdapter(Context context, List<DataItem> mList) {
        this.mList = mList;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_ticket, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.status.setText(mList.get(position).getNamaStatus());
        holder.id.setText(mList.get(position).getId());
        holder.desc.setText(mList.get(position).getDeskripsi());
        holder.mesin.setText(mList.get(position).getNamaMesin());
        holder.time.setText(mList.get(position).getCreated());

        String nomor = mList.get(position).getPelaporWa().replaceFirst("0","+62");
        holder.whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.whatsapp.com/send?phone="+nomor;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mList.size();
    }



    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        TicketAdapter.clickListener = clickListener;
    }



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView time, id, status, desc, mesin;
        LinearLayout whatsapp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.txt_desc);
            status = itemView.findViewById(R.id.txt_status);
            whatsapp = itemView.findViewById(R.id.whatsapp);
            id = itemView.findViewById(R.id.txt_id);
            mesin = itemView.findViewById(R.id.txt_mesin);
            time = itemView.findViewById(R.id.txt_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), itemView);
        }
    }
}