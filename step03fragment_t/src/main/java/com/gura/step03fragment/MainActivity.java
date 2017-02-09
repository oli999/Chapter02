package com.gura.step03fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static class MyFragment extends Fragment{

        // 리턴해주는 View 객체가 MyFragment 의 레이아웃이다(UI).
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // res/layout/fragment_my.xml 문서를 전개해서 View 객체를
            // 만든다.
            View v=inflater.inflate(R.layout.fragment_my, container);
            // 만든 View 객체를 리턴해준다.
            return v;
        }
    }
}





