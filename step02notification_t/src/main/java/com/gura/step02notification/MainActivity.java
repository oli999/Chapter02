package com.gura.step02notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
                implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button makeNotiBtn=(Button)
                findViewById(R.id.makeNotiBtn);
        makeNotiBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.makeNotiBtn:
                makeAutoCancelNoti();
                break;
        }
    }

    //자동 cancel 되는 알림 띄우기
    public void makeAutoCancelNoti(){

    }
}












