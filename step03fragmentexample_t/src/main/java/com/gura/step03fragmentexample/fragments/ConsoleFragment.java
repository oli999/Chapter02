package com.gura.step03fragmentexample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gura.step03fragmentexample.R;

/**
 * Created by user on 2017-02-09.
 */

public class ConsoleFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater
                .inflate(R.layout.fragment_console, container);
        return view;
    }
}
