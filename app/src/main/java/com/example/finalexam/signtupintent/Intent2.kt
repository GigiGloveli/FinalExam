package com.example.finalexam.signtupintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.finalexam.LoginActivity
import com.example.finalexam.finalexam.db
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ge.msda.Finalexam.R
import kotlinx.android.synthetic.main.activity_intent2.*

class Intent2 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbs: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent2)


        dbs = FirebaseDatabase.getInstance().getReference("db")
        auth = FirebaseAuth.getInstance()

        val gender = arrayOf("თავს ვიკავებ", "მდედრობითი", "მამრობითი")

        // ადაპტერი
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            gender // Array
        )

        // ჩამოსასვლელი View-s რესურსის მითითება
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // მონაცემების მიბმა spinner-ზე
        genderspinner.adapter = adapter

        // არჩევის Listener-ის დამატება
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


        next2btn.setOnClickListener {


            val name = intent.extras?.getString("NAME")
            val surname = intent.extras?.getString("SURNAME")

            val number = intentphone.text.toString()
            val age = intentage.text.toString()
            var gender = genderspinner.selectedItem.toString()


            infoset(name!!, surname!!, age, number, gender)
            val intent = Intent(this, LoginActivity::class.java)
            auth.signOut()
            startActivity(intent)
        }


    }

    private fun infoset(name: String, surname: String, age: String, phone: String, gender: String) {

        val database = db(name, surname, age, phone, gender)

        dbs.child(auth.currentUser?.uid!!).setValue(database)
    }
}

