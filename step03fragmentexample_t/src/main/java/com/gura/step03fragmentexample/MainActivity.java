package com.gura.step03fragmentexample;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gura.step03fragmentexample.fragments.ConsoleFragment;
import com.gura.step03fragmentexample.fragments.InputFragment;

public class MainActivity extends AppCompatActivity
        implements InputFragment.InputFragmentListener,
        View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button goNextBtn=(Button)findViewById(R.id.goNextBtn);
        goNextBtn.setOnClickListener(this);
    }

    @Override
    public void sendMessage(String msg) {
        //인자로 전달된 문자열을 ConsoleFragment 객체에 전달한다.
        FragmentManager fragmentManager=getSupportFragmentManager();
        ConsoleFragment cFragment=(ConsoleFragment)
                fragmentManager.findFragmentById(R.id.consoleFragment);
        cFragment.printMessage(msg);
    }

    @Override
    public void onClick(View v) {
        //액티비티 이동
        Intent intent=new Intent(this, SubActivity.class);
        startActivity(intent);
    }
}








