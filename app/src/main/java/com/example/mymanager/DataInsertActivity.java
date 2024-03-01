package com.example.mymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mymanager.databinding.ActivityDataInsertBinding;
import com.example.mymanager.fragments.tasks.tasksfrag;

public class DataInsertActivity extends AppCompatActivity {
    ActivityDataInsertBinding binding;
    String title,disc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDataInsertBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        String type=getIntent().getStringExtra("type");
        if(type.equals("update")){
            setTitle("Update");
//            binding.itime.setHour(getIntent().getStringExtra("time"));
            binding.ititle.setText(getIntent().getStringExtra("title"));
            binding.idisc.setText(getIntent().getStringExtra("desc"));
            int id=getIntent().getIntExtra("id",0);
            binding.add.setText("Update");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    title=binding.ititle.getText().toString();
                    disc=binding.idisc.getText().toString();
                    if(title.isEmpty()||disc.isEmpty()){
                        Toast.makeText(DataInsertActivity.this,"Set your task fields properly",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent();
//                    intent.putExtra("time",binding.itime.getText().toString());
                        intent.putExtra("title", binding.ititle.getText().toString());
                        intent.putExtra("desc", binding.idisc.getText().toString());
                        intent.putExtra("id", id);
//                    intent.putExtra("timeh",binding.itime.getCurrentHour());
//                    intent.putExtra("timem",binding.itime.getCurrentMinute());
//                    String am_pm = (binding.itime.getCurrentHour() < 12) ? "AM" : "PM";
//                    intent.putExtra("timtap",am_pm);
//                    String tm=binding.itime.getCurrentHour()+binding.itime.getCurrentMinute()+binding.itime.getCurrentHour() < 12 ? "AM" : "PM";
                        String hr = binding.itime.getCurrentHour().toString();
                        String mn = binding.itime.getCurrentMinute().toString();
//                    String ap=binding.itime.getCurrentHour() < 12 ? "AM" : "PM";
                        String tm = hr + ":" + mn;
                        intent.putExtra("time", tm);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            });
        }
        else {
            setTitle("Your Task");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    title=binding.ititle.getText().toString();
                    disc=binding.idisc.getText().toString();
                    if(title.isEmpty()||disc.isEmpty()){
                        Toast.makeText(DataInsertActivity.this,"Set your task fields properly",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent();
//                    intent.putExtra("time",binding.itime.getText().toString());
                        intent.putExtra("title", binding.ititle.getText().toString());
                        intent.putExtra("desc", binding.idisc.getText().toString());
//                    intent.putExtra("timeh",binding.itime.getCurrentHour());
//                    intent.putExtra("timem",binding.itime.getCurrentMinute());
//                    String am_pm = (binding.itime.getCurrentHour() < 12) ? "AM" : "PM";
//                    intent.putExtra("timtap",am_pm);
//                    intent.putExtra("id");
                        String hr = binding.itime.getCurrentHour().toString();
                        String mn = binding.itime.getCurrentMinute().toString();
//                    String ap=binding.itime.getCurrentHour() < 12 ? "AM" : "PM";
                        String tm = hr + ":" + mn;
                        intent.putExtra("time", tm);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this,MainActivity.class));
    }
}