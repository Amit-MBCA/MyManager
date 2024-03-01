package com.example.mymanager.fragments.tasks;

import static androidx.recyclerview.widget.ItemTouchHelper.*;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.mediarouter.media.RemotePlaybackClient;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymanager.DataInsertActivity;
import com.example.mymanager.Note;
import com.example.mymanager.NoteViewModel;
import com.example.mymanager.R;
import com.example.mymanager.RVAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class tasksfrag extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public tasksfrag() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment tasksfrag.
//     */
//    public static tasksfrag newInstance(String param1, String param2) {
//        tasksfrag fragment = new tasksfrag();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        noteViewModel=new ViewModelProvider((ViewModelStoreOwner) getActivity(), (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(NoteViewModel.class);
////        if (getArguments() != null) {
////            mParam1 = getArguments().getString(ARG_PARAM1);
////            mParam2 = getArguments().getString(ARG_PARAM2);
////        }
//    }
    FloatingActionButton fab;
    private NoteViewModel noteViewModel;
    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tasksfrag, container, false);
        rv=view.findViewById(R.id.recyclerView);
        fab=view.findViewById(R.id.floatingActionButton);

        noteViewModel=new ViewModelProvider((ViewModelStoreOwner) getActivity(), (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(NoteViewModel.class);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        RVAdapter rvAdapter=new RVAdapter();
        rv.setAdapter(rvAdapter);
        noteViewModel.getAllData().observe(getActivity(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) { //Error may occur here
                rvAdapter.submitList(notes);

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DataInsertActivity.class);
                intent.putExtra("type","addMode");
                startActivityForResult(intent,1);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
//            @Override
//            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//                return 0;
//            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT) {
                    noteViewModel.delete(rvAdapter.getNote(viewHolder.getAdapterPosition()));
                }
                else {
                    Intent intent = new Intent(getActivity(), DataInsertActivity.class);
                    intent.putExtra("type", "update");
                    intent.putExtra("time", rvAdapter.getNote(viewHolder.getAdapterPosition()).getTime());
                    intent.putExtra("title", rvAdapter.getNote(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("desc", rvAdapter.getNote(viewHolder.getAdapterPosition()).getDesc());
                    intent.putExtra("id", rvAdapter.getNote(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent, 2);
//                    Toast.makeText(MainActivity.this,"updating",Toast.LENGTH_SHORT).show();
                }
            }
        }).attachToRecyclerView(rv);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            String title=data.getStringExtra("title");
            String desc=data.getStringExtra("desc");
            String time=data.getStringExtra("time");
            Note note=new Note(time,title,desc);
            noteViewModel.insert(note);
        }
        else if(requestCode==2){
            String title=data.getStringExtra("title");
            String desc=data.getStringExtra("desc");
            String time=data.getStringExtra("time");
            Note note=new Note(time,title,desc);
            note.setId(data.getIntExtra("id",0));
            noteViewModel.update(note);
        }
    }

}