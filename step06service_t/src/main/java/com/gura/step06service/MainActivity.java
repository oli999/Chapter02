package com.gura.step06service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Start Service 버튼을 눌렀을때 호출되는 메소드
    public void start(View v){
        // MyService 를 시작 시키기 위한 Intent 객체
        Intent intent=new Intent(this, MyService.class);
        startService(intent);
    }
    // Stop Service 버튼을 눌렀을때 호출되는 메소드
    public void stop(View v){
        // MyService 를 종료 시키기 위한 Intent 객체
        Intent intent=new Intent(this, MyService.class);
        stopService(intent);
    }
    //죽지 않는 서비스 시작버튼을 눌렀을대 호출되는 메소드
    public void start2(View v){
        Intent intent=new Intent(this, ForeverService.class);
        startService(intent);
    }
}







