package com.example.simplecalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MultiplicationFragment extends Fragment {

    int num1, num2;

    public MultiplicationFragment(int n1, int n2) {
        num1 = n1;
        num2 = n2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplication, container, false);
        TextView result = (TextView) view.findViewById(R.id.mulResult);
        int res =  num1 * num2;
        result.setText("" + res);
        return view;
    }
}