package com.example.finalexam.signtupintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ge.msda.Finalexam.R
import kotlinx.android.synthetic.main.activity_intent1.*

class Intent1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent1)



        next1btn.setOnClickListener() {

            val name = intentname.text.toString()
            val surname = intentsurname.text.toString()

            val intent = Intent(this, Intent2::class.java)
            intent.putExtra("NAME", name)
            intent.putExtra("SURNAME", surname)
            startActivity(intent)
        }
    }


}
