package com.example.finalexam.signtupintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.finalexam.LoginActivity
import com.example.finalexam.finalexam.db
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ge.msda.Finalexam.R
import kotlinx.android.synthetic.main.activity_intent3.*

class Intent3 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var dbs: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent3)

        dbs = FirebaseDatabase.getInstance().getReference("db")
        auth = FirebaseAuth.getInstance()
        intentphoto.setOnClickListener {

            val photourl1 = photourl.text.toString()
           Glide.with(applicationContext).load(photourl1).into(intentphoto)
            intentphoto.setImageResource(0)
        }

        next3btn.setOnClickListener {

            val name = intent.extras?.getString("NAME", "saxeli")
            val surname = intent.extras?.getString("SURNAME", "gvari")
            val age = intent.extras?.getString("AGE", "asaki")
            val number = intent.extras?.getString("NUMBER", "nomeri")
            val gender = intent.extras?.getString("GENDER", "genderi")
            val url = photourl.text.toString()

            infoset(name!!, surname!!, age!!, number!!, gender!!, url)
            val intent = Intent(this, LoginActivity::class.java)
            auth.signOut()
            startActivity(intent)



        }
    }


    private fun infoset(name: String, surname: String, age: String, phone: String, gender: String, url: String) {

        val database = db(name, surname, age, phone,gender, url)

        dbs.child(auth.currentUser?.uid!!).setValue(database)
    }


}


