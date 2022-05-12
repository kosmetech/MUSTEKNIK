package com.project.musteknik.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.github.vipulasri.timelineview.TimelineView;
import com.project.musteknik.R;
import com.project.musteknik.model.timeline.TimelinesItem;

import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder>{
    List<TimelinesItem> mList;
    Context context;
    private LayoutInflater mInflater;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // data is passed into the constructor
    public TimelineAdapter(Context context, List<TimelinesItem> mList) {
        this.mList = mList;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_timeline, null);
        return new ViewHolder(view, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(TimelineAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.dept.setText(mList.get(position).getDepartemen());
        holder.desc.setText(mList.get(position).getDeskripsi());
        holder.time.setText(mList.get(position).getCreatedAt());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TimelineView mTimelineView;
        public TextView dept, desc, time;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            dept = itemView.findViewById(R.id.txt_dept);
            desc = itemView.findViewById(R.id.txt_desc);
            time = itemView.findViewById(R.id.txt_time);
            mTimelineView = (TimelineView) itemView.findViewById(R.id.timeline);
            mTimelineView.initLine(viewType);
        }
    }
}
