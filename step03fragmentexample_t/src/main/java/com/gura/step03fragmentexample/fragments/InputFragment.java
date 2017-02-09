package com.gura.step03fragmentexample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gura.step03fragmentexample.R;

/*
    [ 프레그먼트 클래스 만들기 ]

    - support 페키지에 있는 Fragment 클래스를 상속 받는다.
    - onCreateView() 메소드를 오버라이딩한다.
 */

public class InputFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater
                .inflate(R.layout.fragment_input, container);

        return view;
    }
}
