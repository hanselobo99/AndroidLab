package com.example.simplecalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AdditionFragment extends Fragment {
    int num1, num2;

    public AdditionFragment(int n1, int n2) {
        num1 = n1;
        num2 = n2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addition, container, false);
        TextView result = (TextView) view.findViewById(R.id.addResult);
        int res = num1 + num2;
        result.setText("" + res);
        return view;
    }
}