package com.gura.step06service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;

public class ForeverService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText
                (this, "Forever 서비스 시작!", Toast.LENGTH_SHORT).show();
        //알람 해제
        unRegisterAlarm();

        /*
        1. return START_STICKY;
           메모리가 부족해서 운영체제가 서비스를 kill 시킨다음
           메모리가 여유가 생기면 자동으로 서비스객체를 생성해서 다시 서비스를 시작
           시키는 옵션
        2. return START_NOT_STICKY;
           메모리가 부족해서 운영체제가 서비스를 kill 시킨다음 자동으로
           다시 서비스를 시작 시키지말라는 옵션
        */
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText
                (this, "Forever 서비스 종료!", Toast.LENGTH_SHORT).show();
        //1분후에 깨어날수 있도록 알람을 맞추어 놓는다.
        //AlarmReceiver 에 전달할 Intent 객체
        Intent i=new Intent();
        i.setAction("com.gura.ALARM_RECEIVER");
        // 인텐트 전달자 객체에 Intent 객체를 담는다 .
        PendingIntent pIntent=PendingIntent.getBroadcast(this,0, i, 0);
        // 시스템 부팅이후의 경과 시간 (현재 시간)
        long thisTime= SystemClock.elapsedRealtime();
        // 첫번째 알람 예정시간 (현재시간+60초)
        long alarmTime=thisTime + 1000*60;
        // 알람 메니져의 참조값
        AlarmManager aManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        // 반복 알람을 맞추어 놓는다.
        aManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                alarmTime, //첫번째 알람 예정 시각
                1000*60*3, //반복 주기
                pIntent);  //인텐트 전달자 객체

        super.onDestroy();
    }

    //알람 해제 하는 메소드
    public void unRegisterAlarm(){
        Intent intent=new Intent();
        intent.setAction("com.gura.ALARM_RECEIVER");
        PendingIntent pIntent=PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager aManager=(AlarmManager)
                getSystemService(Context.ALARM_SERVICE);
        aManager.cancel(pIntent);
    }
}











