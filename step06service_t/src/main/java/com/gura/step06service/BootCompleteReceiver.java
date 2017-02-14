package com.gura.step06service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //서비스를 시작 시킨다.
        Intent i=new Intent(context, ForeverService.class);
        context.startService(i);
    }
}
