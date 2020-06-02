package com.fz.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT =2000;
    private long backPress;
    private Toast backToast;
    @Override
    public void onBackPressed() {
        if (backPress + 1 > System.currentTimeMillis())
        {
            backToast.cancel();
            super.onBackPressed();
            backPress = System.currentTimeMillis();
            return;
        }
        else
        {
            backToast = Toast.makeText(getBaseContext(),"Please Wait!",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPress = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this , HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
