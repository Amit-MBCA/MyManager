package com.example.mymanager.fragments.home;

import static android.content.Context.MODE_PRIVATE;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.example.mymanager.Note;
import com.example.mymanager.NoteViewModel;
import com.example.mymanager.R;
import com.example.mymanager.RVAdapter;
import com.example.mymanager.TimeLineAdapter;
import com.example.mymanager.TimeLineModel;
import com.example.mymanager.UserInfo;
import com.github.vipulasri.timelineview.TimelineView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homefrag#} factory method to
 * create an instance of this fragment.
 */
public class homefrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public homefrag() {
//        // Required empty public constructor
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment homefrag.
     */
    // TODO: Rename and change types and number of parameters
//    public static homefrag newInstance(String param1, String param2) {
//        homefrag fragment = new homefrag();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
    private PieChart pc;
    private int i1=60;
    private  int i2=40;
    private CircleImageView primg;
    private Uri uri;
    private TimelineView tlv;
    SharedPreferences userdata;
    private String fname;
    private TextView uname;
    NoteViewModel noteViewModel;
    RecyclerView recyclerView;
    String[] name = {"Event 1", "Event 2", "Event 3", "Event 4", "Event 5", "Event 6", "Event 7", "Event 8", "Event 9", "Event 10", "Event 11", "Event 12"};
    String[] status = {"active", "active", "active","active", "active", "active","inactive", "inactive", "inactive","inactive", "inactive", "inactive"};
    String[] description = {"Description 1","Description 2","Description 3","Description 4","Description 5","Description 6","Description 7","Description 8","Description 9","Description 10","Description 11","Description 12"};
    String[] time = {"12:00 AM", "02:00 AM", "04:00 AM","06:00 AM", "08:00 AM", "10:00 AM","12:00 PM", "02:00 PM", "04:00 PM","06:00 PM", "08:00 PM", "10:00 PM"};
    List<TimeLineModel> timeLineModelList;
    TimeLineModel[] timeLineModel;
    Context context;
    LinearLayoutManager linearLayoutManager;
    RVAdapter rv=new RVAdapter();
    LiveData<List<Note>> list;
//    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_homefrag, container, false);
        noteViewModel=new ViewModelProvider((ViewModelStoreOwner) getActivity(), (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(NoteViewModel.class);
        userdata= getActivity().getSharedPreferences(UserInfo.PREFS_NAME, MODE_PRIVATE);
        fname=userdata.getString("fnamesp","Surbhi");
        uname=(TextView) view.findViewById(R.id.ufirstn);
        pc=(PieChart) view.findViewById(R.id.piecharthome);
        timeLineModelList = new ArrayList<>();
        int size = name.length;
        timeLineModel=new TimeLineModel[size];
        context = getActivity();
        linearLayoutManager = new LinearLayoutManager(getActivity());
//        noteViewModel.getAllData().observe(getActivity(), new Observer<List<Note>>() {
//            @Override
//            public void onChanged(List<Note> notes) { //Error may occur here
////                timelist.add(notes.tit);
//                timelist.addAll((Collection<? extends NoteViewModel>) noteViewModel.getAllData());
//                Toast.makeText((Context) getActivity(), (CharSequence) timelist, Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        rv=view.findViewById(R.id.recyclerView);
        uname.setText(fname);
//        tlv.initLine();
        addtoPieChart();
        SharedPreferences shrd= getActivity().getSharedPreferences(UserInfo.PREFS_NAME, Context.MODE_PRIVATE);
        String previouslyEncodedImage = shrd.getString("image_data","Nothing");
        uri= Uri.parse(previouslyEncodedImage);
        primg=view.findViewById(R.id.home_img);
        primg.setImageURI(uri);

        HorizontalStepView setpview5 = (HorizontalStepView) view.findViewById(R.id.step_view);
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("First",1);
        StepBean stepBean1 = new StepBean("Second",1);
        StepBean stepBean2 = new StepBean("Third",1);
        StepBean stepBean3 = new StepBean("Fourth",1);
        StepBean stepBean4 = new StepBean("Fifth",1);
        StepBean stepBean5 = new StepBean("Sixth",1);
        StepBean stepBean6 = new StepBean("Seventh",0);
        StepBean stepBean7 = new StepBean("Eighth",-1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);
        stepsBeanList.add(stepBean4);
        stepsBeanList.add(stepBean5);
        stepsBeanList.add(stepBean6);
        stepsBeanList.add(stepBean7);


        setpview5
                .setStepViewTexts(stepsBeanList)//总步骤
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getActivity(), R.color.completed_text_color))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getActivity(), R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(getActivity(),R.color.completed_text_color))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getActivity(), R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_check_circle_24))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_remove_circle_24))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getActivity(), R.drawable.currentstep_24));//设置StepsViewIndicator AttentionIcon
//        for (int i = 0; i < size; i++) {
//            timeLineModel[i] = new TimeLineModel();
//            timeLineModel[i].setName(name[i]);
//            timeLineModel[i].setStatus(status[i]);
//            timeLineModel[i].setDescription(description[i]);
//            timeLineModel[i].setTime(time[i]);
//            timeLineModelList.add(timeLineModel[i]);
//        }
//        recyclerView = (RecyclerView) view.findViewById(R.id.tlrecylerview);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);  //for divider
//
//        recyclerView.setAdapter(new TimeLineAdapter(context, timeLineModelList));
        return view;
    }
    private void addtoPieChart() {
        pc.addPieSlice(new PieModel("Passed",i1, Color.parseColor("#00539f")));
        pc.addPieSlice(new PieModel("Remaining",i2, Color.parseColor("#FEE71F")));
        pc.startAnimation();
    }
}