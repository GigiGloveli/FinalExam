package com.example.finalexam.signtupintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import ge.msda.Finalexam.R
import kotlinx.android.synthetic.main.activity_intent2.*

class Intent2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent2)
        val gender = arrayOf("თავს ვიკავებ", "მდედრობითი", "მამრობითი")

        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            gender // Array
        )

        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with dapter
        genderspinner.adapter = adapter

        // Set an on item selected listener for spinner object
        genderspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Display the selected item text on text view
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }


        next2btn.setOnClickListener() {

            val name = intent.extras?.getString("NAME")
            val surname = intent.extras?.getString("SURNAME")

            val number = intentphone.text.toString()
            val age = intentage.text.toString()

            var gender = genderspinner.selectedItem.toString()


            val intent = Intent(this, Intent3::class.java)
            intent.putExtra("NAME", name)
            intent.putExtra("SURNAME", surname)
            intent.putExtra("NUMBER", number)
            intent.putExtra("AGE", age)
            intent.putExtra("GENDER", gender)
            startActivity(intent)
        }
    }
}

