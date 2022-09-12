package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button add, sub, mul, div;
    EditText num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = findViewById(R.id.number1);
        num2 = findViewById(R.id.number2);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
    }

    public void replaceFragment(Fragment fragment, int id) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment)
                .addToBackStack("")
                .commit();
    }

    public void calculate(View view) {
        int no1 = Integer.parseInt(num1.getText().toString());
        int no2 = Integer.parseInt(num2.getText().toString());

        switch (view.getId()) {
            case R.id.add:
                replaceFragment(new AdditionFragment(no1,no2), R.id.forAdd);
                break;
            case R.id.sub:
                replaceFragment(new SubtractionFragment(no1,no2), R.id.forSub);
                break;
            case R.id.mul:
                replaceFragment(new MultiplicationFragment(no1,no2), R.id.forMul);
                break;
            case R.id.div:
                replaceFragment(new DivisionFragment(no1,no2), R.id.forDiv);
                break;
            default:
                break;
        }
    }

}