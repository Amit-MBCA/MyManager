package com.example.mymanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfo extends AppCompatActivity {
    private CircleImageView img;
    private ImageButton imgbtn;
    private AppCompatButton saveinfo;
    private EditText etfirstname,etlastname,etcontact,etoccupation,etdescription;
    private String fname,lname,cont,occu,desc,imgdata;
    private String spfname,splname,spcont,spoccu,spdesc;
    private Uri imguri,uri,pimguri;
    public boolean check,prpic;
    SharedPreferences userdata;
    private static int flag=0;
    public static String PREFS_NAME="MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        getSupportActionBar().hide();
//        check=userdata.getBoolean("hasSignedUp",false);
        saveinfo=(AppCompatButton) findViewById(R.id.saveinfobtn);
        img=findViewById(R.id.profile_image);
        imgbtn=findViewById(R.id.imageButton);
        etfirstname=(EditText) findViewById(R.id.etfirstname);
        etlastname=(EditText) findViewById(R.id.etlastname);
        etcontact=(EditText) findViewById(R.id.etcontact);
        etoccupation=(EditText) findViewById(R.id.etoccupation);
        etdescription=(EditText) findViewById(R.id.etdesciption);
        userdata=getSharedPreferences(UserInfo.PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = userdata.edit();
//        pimguri=Uri.parse("android.resource://com.example.mymanager/drawable/appiconwhitemd");
//        imgdata=pimguri.toString();

        //check - to check the user signup for the first time or existing user
        check=userdata.getBoolean("hasSignedUp",false);

          if(check) {
        String imgdata=userdata.getString("image_data","null");
        Uri imguri=Uri.parse(imgdata);
        img.setImageURI(imguri);
        spfname=userdata.getString("fnamesp","Surbhi");
        splname=userdata.getString("lnamesp","Pareek");
        spcont=userdata.getString("contsp","86382*****");
        spoccu=userdata.getString("occusp","CEO");
        spdesc=userdata.getString("descsp","Manager");
        etfirstname.setText(spfname);
        etlastname.setText(splname);
        etcontact.setText(spcont);
        etoccupation.setText(spoccu);
        etdescription.setText(spdesc);
          }
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(UserInfo.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080,1080)
                        .start();
//                        .galleryOnly() // it is used when we want image only from gallery not from camera
//                        .cameraOnly()
//                        .cropOval()
            }
        });
        saveinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=etfirstname.getText().toString();
                lname=etlastname.getText().toString();
                cont=etcontact.getText().toString();
                occu=etoccupation.getText().toString();
                desc=etdescription.getText().toString();
                prpic=userdata.getBoolean("prvalid",false);
//                userdata=getSharedPreferences(UserInfo.PREFS_NAME,MODE_PRIVATE);
//                SharedPreferences.Editor editor = userdata.edit();
                if((desc.isEmpty())||(fname.isEmpty())||(lname.isEmpty())||(occu.isEmpty())||(cont.isEmpty())){
                    Toast.makeText(UserInfo.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }
                else{
//                    if(img.getDrawable()==null&&!check){
                    if(!prpic){
                        Toast.makeText(UserInfo.this, "Please set your profile pic", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent gotomain = new Intent(UserInfo.this, MainActivity.class);
                        boolean hsp=true;
                        editor.putBoolean("hasSignedUp",true);
                        editor.putString("fnamesp",fname);
                        editor.putString("lnamesp",lname);
                        editor.putString("contsp",cont);
                        editor.putString("occusp",occu);
                        editor.putString("descsp",desc);
                        editor.commit();
                        startActivity(gotomain);
                        finish();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            uri= data.getData();
            img.setImageURI(uri);
        userdata=getSharedPreferences(UserInfo.PREFS_NAME,MODE_PRIVATE);
        //check - to check the user signup for the first time or existing user
        SharedPreferences.Editor editor=userdata.edit();
        if(resultCode==RESULT_OK){
            flag=1;
            String encodedImage=uri.toString();
            editor.putString("image_data",encodedImage);
            editor.putBoolean("prvalid",true);
            editor.apply();
//            img.setImageURI(uri);
            Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Image not captured", Toast.LENGTH_SHORT).show();
        }
    }
}