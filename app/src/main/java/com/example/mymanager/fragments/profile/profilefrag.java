package com.example.mymanager.fragments.profile;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mymanager.R;
import com.example.mymanager.UserInfo;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class profilefrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public profilefrag() {
//        // Required empty public constructor
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment profilefrag.
     */
    // TODO: Rename and change types and number of parameters
//    public static profilefrag newInstance(String param1, String param2) {
//        profilefrag fragment = new profilefrag();
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
    private ImageButton editinfo;
    private CircleImageView primg;
    private Uri uri;
    private String fname,lname,occu,disc,cont;
    private TextView tvfname,tvlname,tvcont,tvoccu,tvdisc;
    SharedPreferences userdata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profilefrag, container, false);
        editinfo=(ImageButton) view.findViewById(R.id.editinfobtn);
        editinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), UserInfo.class);
               startActivity(intent);}
            });
        pc=(PieChart) view.findViewById(R.id.piechart);
        addtoPieChart();
        userdata= getActivity().getSharedPreferences(UserInfo.PREFS_NAME, MODE_PRIVATE);
        String previouslyEncodedImage = userdata.getString("image_data","Nothing");
        fname=userdata.getString("fnamesp","Surbhi");
        lname=userdata.getString("lnamesp","Pareek");
        cont=userdata.getString("contsp","9518805186");
        occu=userdata.getString("occusp","Manager");
        disc=userdata.getString("descsp","eAll");
        tvfname=(TextView) view.findViewById(R.id.uname);
        tvcont=(TextView) view.findViewById(R.id.ucont);
        tvoccu=(TextView) view.findViewById(R.id.uoccu);
        tvdisc=(TextView) view.findViewById(R.id.udisc);
        uri=Uri.parse(previouslyEncodedImage);
        primg=view.findViewById(R.id.prof_img);
        primg.setImageURI(uri);
        tvfname.setText(fname+" "+lname);
        tvcont.setText(cont);
        tvoccu.setText(occu);
        tvdisc.setText(disc);
        return view;
    }

    private void addtoPieChart() {
        pc.addPieSlice(new PieModel("Passed",i1, Color.parseColor("#00539f")));
        pc.addPieSlice(new PieModel("Remaining",i2, Color.parseColor("#FEE71F")));
        pc.startAnimation();
    }
}