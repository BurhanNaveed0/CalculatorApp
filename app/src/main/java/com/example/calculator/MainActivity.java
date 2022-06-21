package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Textview containing calculations
    TextView calc;

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button b0;

    // Misc. Buttons
    Button multButton;
    Button divButton;
    Button plusButton;
    Button minusButton;
    Button clearButton;
    Button equalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = findViewById(R.id.id_calc);
        calc.setText("");

        b1 = findViewById(R.id.id_button_1);
        b2 = findViewById(R.id.id_button_2);
        b3 = findViewById(R.id.id_button_3);
        b4 = findViewById(R.id.id_button_4);
        b5 = findViewById(R.id.id_button_5);
        b6 = findViewById(R.id.id_button_6);
        b7 = findViewById(R.id.id_button_7);
        b8 = findViewById(R.id.id_button_8);
        b9 = findViewById(R.id.id_button_9);
        b0 = findViewById(R.id.id_button_0);

        clearButton = findViewById(R.id.id_button_clear);
        multButton = findViewById(R.id.id_button_mult);
        divButton = findViewById(R.id.id_button_div);
        plusButton = findViewById(R.id.id_button_plus);
        minusButton = findViewById(R.id.id_button_minus);
        equalButton = findViewById(R.id.id_button_equals);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);

        clearButton.setOnClickListener(this);
        multButton.setOnClickListener(this);
        divButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        equalButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        CharSequence origText = calc.getText();
        CharSequence newText = ((Button) view).getText();

        if(view.getId() == R.id.id_button_clear) {
            calc.setText("");
        } else if(view.getId() == R.id.id_button_equals) {
            String inputArr = calc.getText().toString();
            calc.setText(eval(inputArr));
        } else {
            if(origText.equals("Error")) {
                calc.setText("");
                origText = calc.getText();
            }

            calc.setText(origText.toString() + newText.toString());
        }
    }

    public String eval(String input) {
        ArrayList<String> inputList = new ArrayList<String>();

        // Fill array list (Separate numbers and operators)

        /*
        inputList.add(inputArray[i]);
        Log.d("list", inputArray[i]);
         */

        String[] inputArr = input.split("");
        String temp = "";

        for(int i = 0; i < inputArr.length; i++) {
            if(inputArr[i].equals("*") || inputArr[i].equals("-") || inputArr[i].equals("+") || inputArr[i].equals("/")) {
                if(temp.length() > 0)
                    inputList.add(temp);
                inputList.add(inputArr[i]);

                temp = "";
            } else {
                temp += inputArr[i];
            }
        }

        inputList.add(temp);

        for(int i = 0; i < inputList.size(); i++) {
            Log.d("initialList", inputList.get(i));
        }

        // Run through division and multiplication
        for(int i = 0; i < inputList.size(); i++) {
            if(inputList.get(i).equals("/")) {
                try {
                    double ans = Double.parseDouble(inputList.get(i-1)) / Double.parseDouble(inputList.get(i+1));

                    inputList.set(i, Double.toString(ans));
                    inputList.remove(i+1);
                    inputList.remove(i-1);

                    i -= 1;
                } catch (Exception e) {
                    return "Error";
                }

            } else if(inputList.get(i).equals("*")) {
                try {
                    double ans = Double.parseDouble(inputList.get(i-1)) * Double.parseDouble(inputList.get(i+1));

                    inputList.set(i, Double.toString(ans));
                    inputList.remove(i+1);
                    inputList.remove(i-1);

                    i -= 1;
                } catch(Exception e) {
                    return "Error";
                }
            }
        }

        for(int i = 0; i < inputList.size(); i++) {
            if(inputList.get(i).equals("+")) {
                try {
                    double ans = Double.parseDouble(inputList.get(i-1)) + Double.parseDouble(inputList.get(i+1));

                    inputList.set(i, Double.toString(ans));
                    inputList.remove(i+1);
                    inputList.remove(i-1);

                    i -= 1;
                } catch(Exception e) {
                    return "Error";
                }
            } else if(inputList.get(i).equals("-")) {
                try {
                    double ans = Double.parseDouble(inputList.get(i-1)) - Double.parseDouble(inputList.get(i+1));

                    inputList.set(i, Double.toString(ans));
                    inputList.remove(i+1);
                    inputList.remove(i-1);

                    i -= 1;
                } catch (Exception e) {
                    return "Error";
                }
            }
        }

        for(int i = 0; i < inputList.size(); i++) {
            Log.d("list", "value: " + inputList.get(i));
            Log.d("list", "index: " + i);
        }

        if(inputList.get(0).equals("Infinity"))
            inputList.set(0, "Error");
        if(inputList.get(0).equals("NaN"))
            inputList.set(0, "Error");
        return inputList.get(0);

    }
}
