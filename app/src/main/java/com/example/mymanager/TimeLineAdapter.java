package com.example.mymanager;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.baoyachi.stepview.VerticalStepView;
import com.example.mymanager.fragments.home.homefrag;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.ArrayList;
import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TimeLineModel> timeLineModelList;
    private Context context;
    VerticalStepView vsv;

    public TimeLineAdapter(Context context, List<TimeLineModel> timeLineModelList) {
        this.timeLineModelList = timeLineModelList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_layout, parent, false);
        return new ViewHolder(view, viewType);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).textView.setText(timeLineModelList.get(position).getName());
        ((ViewHolder) holder).textViewDescription.setText(timeLineModelList.get(position).getDescription());
        ((ViewHolder)holder).textViewTime.setText(timeLineModelList.get(position).getTime());
        List<String> sources=new ArrayList<>();
        sources.add("Start");
        sources.add("Design");
        sources.add("Coding");
        sources.add("Testing");
        sources.add("Maintenance");

        vsv.setStepsViewIndicatorComplectingPosition(sources.size()-2).reverseDraw(false).setStepViewTexts(sources)
                .setLinePaddingProportion(0.85f).setStepsViewIndicatorCompletedLineColor(Color.parseColor("#FFFF00"))
                .setStepViewComplectedTextColor(Color.parseColor("#FFFFFF"))
                .setStepViewUnComplectedTextColor(Color.parseColor("#FFFFFF"))
                .setStepsViewIndicatorUnCompletedLineColor(Color.parseColor("#ff0000"))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_baseline_check_circle_24))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.ic_baseline_remove_circle_24))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.ic_baseline_remove_circle_24));

//        if (timeLineModelList.get(position).getStatus().equals("inactive"))
//            ((ViewHolder) holder).timelineView.setMarker(context.getResources().getDrawable(R.drawable.ic_baseline_remove_circle_24));
////            ((ViewHolder) holder).timelineView.setMarker(context.getDrawable(ic_remove_circle_outline_black_24dp));
//        else
//            ((ViewHolder) holder).timelineView.setMarker(context.getDrawable(R.drawable.ic_baseline_check_circle_24));
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return timeLineModelList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        VerticalStepView timelineView;
        TextView textView,textViewDescription,textViewTime;

        ViewHolder(View itemView, int viewType) {
            super(itemView);
            timelineView = itemView.findViewById(R.id.verticalsv);
            textView = itemView.findViewById(R.id.row_timeline_layout_text_view_name);
            textViewDescription = itemView.findViewById(R.id.row_timeline_layout_text_view_description);
            textViewTime = itemView.findViewById(R.id.row_timeline_layout_text_view_time);
//            timelineView.initLine(viewType);
        }
    }
}

