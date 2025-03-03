package com.zybooks.pizzaparty

import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//const val SLICES_PER_PIZZA = 8

class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup

    /**
     * onCreate() is called when the activity starts and loads the activity's XML layout.
     * setContentView() sets the activity's content to the layout defined in activity_main.xml.
     * findViewById() returns a View from the layout file that matches the given ID.
     * These Views include the EditText, TextView, and RadioGroup widgets.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)

//        // TextWatcher
//        numAttendEditText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                // If user starts typing number of people attending, then set s to empty string
//                numAttendEditText.setText("")
//            }
//            override fun afterTextChanged(s: Editable?) {}
//        })
    }

    /**
     * When the user types a number and selects a radio button, and then clicks the Calculate button,
     * this method is called.
     * The total number of pizzas is calculated by the user input number of attendees and returned.
     * MainActivity.kt is the Controller in MVC
     */
    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.checkedRadioButtonId) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        // Using the PizzaCalculator.kt class to calculate the total number of pizzas needed
        // based on the number of attendees and hunger level.
        val calc = PizzaCalculator(numAttend, hungerLevel)
        val totalPizzas = calc.totalPizzas

        // Place totalPizzas into the string resource and display
        val totalText = getString(R.string.total_pizzas_num, totalPizzas)
        numPizzasTextView.text = totalText
    }


//    fun calculateClick(view: View) {
//
//        /** Get the text that was typed into the EditText */
//        val numAttendStr = numAttendEditText.text.toString()
//
//        /** Convert the text into an integer */
//        val numAttend = numAttendStr.toInt()
//
//        /** Determine how many slices on average each person will eat */
//        val slicesPerPerson = when (howHungryRadioGroup.checkedRadioButtonId) {
//            R.id.light_radio_button -> 2
//            R.id.medium_radio_button -> 3
//            else -> 4
//        }
//
//        /** Calculate and show the number of pizzas needed */
//        val totalPizzas = ceil(numAttend * slicesPerPerson / SLICES_PER_PIZZA.toDouble()).toInt()
//        numPizzasTextView.text = "Total pizzas: $totalPizzas"
//    }
}