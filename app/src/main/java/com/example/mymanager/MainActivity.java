package com.example.mymanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mymanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SharedPreferences userdata;
    private static final int SPLASH_TIME_OUT=0;
    public static String PREFS_NAME="MyPrefsFile";
    public boolean hasSignedUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userdata=getSharedPreferences(UserInfo.PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=userdata.edit();
        editor.putBoolean("loggedIn",false);
        new Handler().postDelayed(() -> {
            userdata = getSharedPreferences(UserInfo.PREFS_NAME, 0);
            hasSignedUp= userdata.getBoolean("hasSignedUp", false);
//              boolean check=hasSignedUp;
        if (!hasSignedUp) {
            Intent intent = new Intent(MainActivity.this,UserInfo.class);
            startActivity(intent);
            finish();
            }
        },SPLASH_TIME_OUT);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_tasks, R.id.navigation_profile, R.id.navigation_about)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}