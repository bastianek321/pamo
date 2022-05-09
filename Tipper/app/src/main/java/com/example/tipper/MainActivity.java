package com.example.tipper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.view.View;
import android.widget.Button;
import android.widget.EditText; // for bill amount input
import android.widget.TextView; // for displaying text

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MainActivity extends AppCompatActivity {
    private static final NumberFormat numberFormat =
            NumberFormat.getNumberInstance();

    private double mass = 0.0;
    private double height = 0.0;
    private int age = 0;
    private double bmi = 0;
    private TextView massTextView;
    private TextView heightTextView;
    private TextView bmiTextView;
    private TextView ageTextView;
    private TextView calorieTextView;
    private int checkupNumber = 0;
    private LineGraphSeries<DataPoint> graphData;
    private GraphView graphView;

    // called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        graphData = new LineGraphSeries<DataPoint>(new DataPoint[]{});
        // get references to programmatically manipulated TextViews
        massTextView = findViewById(R.id.massTextView);
        heightTextView = findViewById(R.id.heightTextView);
        bmiTextView = findViewById(R.id.bmiTextView);
        ageTextView = findViewById(R.id.ageTextView);
        calorieTextView = findViewById(R.id.recommendedCalorie);
        ageTextView.setText(numberFormat.format(0));
        massTextView.setText(numberFormat.format(0));
        heightTextView.setText(numberFormat.format(0));
        bmiTextView.setText(numberFormat.format(0));
        calorieTextView.setText(numberFormat.format(0));
        graphView = findViewById(R.id.graphView);

        // set amountEditText's TextWatcher
        EditText amountEditText =
                (EditText) findViewById(R.id.massEditText);
        amountEditText.addTextChangedListener(massEditTextWatcher);

        EditText heightEditText =
                (EditText) findViewById(R.id.heightEditText);
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        EditText ageEditText = (EditText) findViewById(R.id.ageEditText);
        ageEditText.addTextChangedListener(ageEditTextWatcher);

        Button recipesButton = (Button) findViewById(R.id.recipesButton);
        recipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecipesActivity.class));
            }
        });

        Button calculateButton = (Button) findViewById(R.id.calculateBmiButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                calculateCalories();
                drawGraph();
            }
        });
    }

    // calculate and display tip and total amounts
    private void calculate() {

        // calculate the tip and total
        bmi = mass / Math.pow(height, 2);
        // done this way because List.of doesn't work

        // display tip and total formatted as currency
        bmiTextView.setText(Double.toString(bmi));
    }

    private void calculateCalories() {
        //For men: BMR = 66.5 + (13.75 * weight in kg) + (5.003 * height in cm) - (6.75 * age)
        //
        //For women: BMR = 655.1 + (9.563 * weight in kg) + (1.850 * height in cm) - (4.676 * age)

        double calorieIntakeMen = 66.5 + (13.75 * mass) + (5.003 * (height * 100)) - (6.75 * age);
        double calorieIntakeWomen = 655.1 + (9.563 * mass) + (1.850 * (height * 100)) - (4.676 * age);
        double averageCalories = ((calorieIntakeMen + calorieIntakeWomen) / 2);

        calorieTextView.setText(String.format("%.2f", averageCalories));
    }

    private void drawGraph(){
        // after adding data to our line graph series.
        // on below line we are setting
        // title for our graph view.
        checkupNumber += 1;
        if(graphData.getHighestValueX() < age){
            graphData.appendData(new DataPoint(age, bmi), true, checkupNumber);
        }
        graphView.setTitle("BMI change in time");

        // on below line we are setting
        // text color to our graph view.
        graphView.setTitleColor(R.color.colorPrimary);

        // on below line we are setting
        // our title text size.
        graphView.setTitleTextSize(68);

        // on below line we are adding
        // data series to our graph view.
        graphView.addSeries(graphData);
    }


    private final TextWatcher ageEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                age = Integer.parseInt(charSequence.toString());
                ageTextView.setText(Integer.toString(age));
            } catch (NumberFormatException e) {
                ageTextView.setText("");
                age = 0;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    // listener object for the SeekBar's progress changed events
    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get bill amount and display currency formatted value
                height = Double.parseDouble(s.toString());
                heightTextView.setText(Double.toString(height));
            } catch (NumberFormatException e) { // if s is empty or non-numeric
                heightTextView.setText("");
                height = 0.0;
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };

    // listener object for the EditText's text-changed events
    private final TextWatcher massEditTextWatcher = new TextWatcher() {
        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get bill amount and display currency formatted value
                mass = Double.parseDouble(s.toString());
                massTextView.setText(Double.toString(mass));
            } catch (NumberFormatException e) { // if s is empty or non-numeric
                massTextView.setText("");
                mass = 0.0;
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };
}


/*************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
