package com.gura.step06service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/*
    [ UI 없이 백그라운드에서 동작할수 있는 Service ]

    1. Service 추상 클래스를 상속 받아서 만든다.
    2. onBind() 메소드를 오버라이딩한다.
    3. AndroidManifest.xml 에 등록 되어 있어야 한다.
    4. Intent 객체로 활성화 시킬수 있다.
    5. 백그라운드에서 서버와의 통신을 하거나, 음악 재생등의
       작업을 주로 한다.
 */
public class MyService extends Service {

    //서비스 객체가 생성된 직후에 호출되는 메소드
    @Override
    public void onCreate() {
        super.onCreate();
        //서비스의 초기화 작업을 여기서 해준다.
    }

    //다른 객체 (주로 Activity) 와 연결 되었을때 호출되는 메소드
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    //서비스가 시작 되는 시점에 한번 호출되는 메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText
                (this, "Service 가 시작 됩니다.", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }
    //서비스가 종료 되는 시점에 호출되는 메소드
    @Override
    public void onDestroy() {
        //종료 되기 전에 마무리 작업은 super.onDestroy() 가 호출되기
        //전에 여기서 해준다.
        Toast.makeText
                (this, "Service 가 종료 됩니다.", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}














