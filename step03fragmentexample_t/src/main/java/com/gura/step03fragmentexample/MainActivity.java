package com.gura.step03fragmentexample;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gura.step03fragmentexample.fragments.ConsoleFragment;
import com.gura.step03fragmentexample.fragments.InputFragment;

public class MainActivity extends AppCompatActivity
        implements InputFragment.InputFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void sendMessage(String msg) {
        //인자로 전달된 문자열을 ConsoleFragment 객체에 전달한다.
        FragmentManager fragmentManager=getSupportFragmentManager();
        ConsoleFragment cFragment=(ConsoleFragment)
                fragmentManager.findFragmentById(R.id.consoleFragment);
        cFragment.printMessage(msg);
    }
}








