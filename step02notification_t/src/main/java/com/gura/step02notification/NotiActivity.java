package com.gura.step02notification;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NotiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti);
        //알림을 띄우면서 전달된 인텐트 객체의 참조값을 얻어온다.
        Intent intent=getIntent();
        // "msg" 라는 키값으로 저장된 String type 데이터를 읽어온다
        String msg = intent.getStringExtra("msg");
        // TextView 의 참조값을 얻어와서
        TextView textView=(TextView)findViewById(R.id.textView);
        // 출력해보기
        textView.setText(msg);
        //버튼의 참조값 얻어와서 리스너 등록하기
        Button clearNoti=(Button)findViewById(R.id.clearNoti);
        clearNoti.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //알림 지우기
                NotificationManager notiManager=(NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                //알림의 식별값을 이용해서 삭제한다.
                notiManager.cancel(AppConstants.NOTI_ID);
            }
        });
    }
}









