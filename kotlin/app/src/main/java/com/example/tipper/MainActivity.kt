package com.example.tipper
// for EditText event handling
// EditText listener
// for bill amount input
// for displaying text
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.lang.NumberFormatException
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private var mass = 0.0
    private var height = 0.0
    private var age: Double = 0.0
    private var bmi = 0.0
    private var massTextView: TextView? = null
    private var heightTextView: TextView? = null
    private var bmiTextView: TextView? = null
    private var ageTextView: TextView? = null
    private var calorieTextView: TextView? = null
    private var checkupNumber = 0
    private var graphData: LineGraphSeries<DataPoint>? = null
    private var graphView: GraphView? = null

    // called when the activity is first created
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // call superclass onCreate
        setContentView(R.layout.activity_main) // inflate the GUI
        graphData = LineGraphSeries<DataPoint>(arrayOf<DataPoint>())
        // get references to programmatically manipulated TextViews
        massTextView = findViewById<TextView>(R.id.massTextView)
        heightTextView = findViewById<TextView>(R.id.heightTextView)
        bmiTextView = findViewById<TextView>(R.id.bmiTextView)
        ageTextView = findViewById<TextView>(R.id.ageTextView)
        calorieTextView = findViewById<TextView>(R.id.recommendedCalorie)
        ageTextView?.setText(numberFormat.format(0))
        massTextView?.setText(numberFormat.format(0))
        heightTextView?.setText(numberFormat.format(0))
        bmiTextView?.setText(numberFormat.format(0))
        calorieTextView?.setText(numberFormat.format(0))
        graphView = findViewById(R.id.graphView)

        // set amountEditText's TextWatcher
        val amountEditText: EditText = findViewById<View>(R.id.massEditText) as EditText
        amountEditText.addTextChangedListener(massEditTextWatcher)
        val heightEditText: EditText = findViewById<View>(R.id.heightEditText) as EditText
        heightEditText.addTextChangedListener(heightEditTextWatcher)
        val ageEditText: EditText = findViewById<View>(R.id.ageEditText) as EditText
        ageEditText.addTextChangedListener(ageEditTextWatcher)
        val recipesButton = findViewById<View>(R.id.recipesButton) as Button
        recipesButton.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    RecipesActivity::class.java
                )
            )
        }
        val calculateButton = findViewById<View>(R.id.calculateBmiButton) as Button
        calculateButton.setOnClickListener {
            calculate()
            calculateCalories()
            drawGraph()
        }
    }

    // calculate and display tip and total amounts
    private fun calculate() {

        // calculate the tip and total
        bmi = mass / Math.pow(height, 2.0)
        // done this way because List.of doesn't work

        // display tip and total formatted as currency
        bmiTextView?.setText(java.lang.Double.toString(bmi))
    }

    private fun calculateCalories() {
        //For men: BMR = 66.5 + (13.75 * weight in kg) + (5.003 * height in cm) - (6.75 * age)
        //
        //For women: BMR = 655.1 + (9.563 * weight in kg) + (1.850 * height in cm) - (4.676 * age)
        val calorieIntakeMen = 66.5 + 13.75 * mass + 5.003 * (height * 100) - 6.75 * age
        val calorieIntakeWomen = 655.1 + 9.563 * mass + 1.850 * (height * 100) - 4.676 * age
        val averageCalories = (calorieIntakeMen + calorieIntakeWomen) / 2
        calorieTextView?.setText(String.format("%.2f", averageCalories))
    }

    private fun drawGraph() {
        // after adding data to our line graph series.
        // on below line we are setting
        // title for our graph view.
        checkupNumber += 1
        if (graphData?.getHighestValueX()!! < age) {
            graphData?.appendData(DataPoint(age, bmi), true, checkupNumber)
        }
        graphView?.setTitle("BMI change in time")

        // on below line we are setting
        // text color to our graph view.
        graphView?.setTitleColor(R.color.colorPrimary)

        // on below line we are setting
        // our title text size.
        graphView?.setTitleTextSize(68F)

        // on below line we are adding
        // data series to our graph view.
        graphView?.addSeries(graphData)
    }

    private val ageEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            try {
                age = charSequence.toString().toDouble()
                ageTextView?.setText(age.toString())
            } catch (e: NumberFormatException) {
                ageTextView?.setText("")
                age = 0.0
            }
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {}
    }

    // listener object for the SeekBar's progress changed events
    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies the bill amount
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            try { // get bill amount and display currency formatted value
                height = s.toString().toDouble()
                heightTextView?.setText(java.lang.Double.toString(height))
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                heightTextView?.setText("")
                height = 0.0
            }
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }

    // listener object for the EditText's text-changed events
    private val massEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies the bill amount
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            try { // get bill amount and display currency formatted value
                mass = s.toString().toDouble()
                massTextView?.setText(java.lang.Double.toString(mass))
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                massTextView?.setText("")
                mass = 0.0
            }
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }

    companion object {
        private val numberFormat = NumberFormat.getNumberInstance()
    }
}