package com.gura.step01broadcastreceiver;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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
        //방송하기 버튼의 참조값 얻어와서 리스너 등록 하기
        Button broadcastBtn=(Button)findViewById(R.id.broadcastBtn);
        broadcastBtn.setOnClickListener(this);

        Button broadcastBtn2=(Button)findViewById(R.id.broadcastBtn2);
        broadcastBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.broadcastBtn:
                // 방송할 인텐트 객체를 생성하고
                Intent intent=new Intent();
                // 방송의 이름 (식별값) 을 정하고
                intent.setAction("com.gura.MERONG");
                // 방송한다.
                sendBroadcast(intent);
                break;
            case R.id.broadcastBtn2:
                // 10초 후에 Handler 객체에 빈메세지 보내기
                // 결과적으로 10초 후에 핸들러객체의 handleMessage() 메소드가
                // 호출된다.
                handler.sendEmptyMessageDelayed(0, 10000);

                break;
        }
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // 방송할 인텐트 객체를 생성하고
            Intent intent2=new Intent();
            // 방송의 이름 (식별값) 을 정하고
            intent2.setAction("com.gura.MERONG");
            // 방송한다.
            sendBroadcast(intent2);
        }
    };

}











