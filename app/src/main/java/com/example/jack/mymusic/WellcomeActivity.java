package com.example.jack.mymusic;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.jack.mymusic.utils.AppConfigUtils;

public class WellcomeActivity extends AppCompatActivity {

    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        handler.postDelayed(runnable,2000);
    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            boolean guide = AppConfigUtils.getInstance().getGuide(WellcomeActivity.this);

            if(guide) {
                startActivity(new Intent(WellcomeActivity.this, GuideActivity.class));
                finish();
            }else {
                startActivity(new Intent(WellcomeActivity.this,MainActivity.class));
            }
        }

    };

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }
}
