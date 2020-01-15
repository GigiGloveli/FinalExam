package com.example.finalexam

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.finalexam.finalexam.db
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ge.msda.Finalexam.R
import kotlinx.android.synthetic.main.activity_main.*

class FirstpageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbs: DatabaseReference
    var glidepic = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        dbs = FirebaseDatabase.getInstance().getReference("db")

        if (auth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContentView(R.layout.activity_main)

        addUserInfoChangeListener()

        Glide.with(applicationContext).load(glidepic).into(personalinfo)

        logoutBtn.setOnClickListener {

            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

        refreshbtn.setOnClickListener {
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
            Toast.makeText(this, "ვუუუუუმმმმმ", Toast.LENGTH_SHORT).show()
        }

        editbtn.setOnClickListener {
            val intent = Intent(this, UpdateinfoActivity::class.java)
            startActivity(intent)

        }


    }

    private fun addUserInfoChangeListener() {

        dbs.child(auth.currentUser?.uid!!)
            .addValueEventListener(object : ValueEventListener {

                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(snap: DataSnapshot) {

                    val data: db = snap.getValue(db::class.java) ?: return

                    nameview.text = "სახელი - ${data.name}"
                    surnameview.text = "გვარი - ${data.surname}"
                    ageview.text = "ასაკი - ${data.age}"
                    phoneview.text = "ტელ - ${data.number}"
                    genderview.text = "სქესი - ${data.gender}"
                    mailview.text = "მეილი - ${auth.currentUser?.email.toString()}"

                    glidepic = data.url

                }

            })
    }
}
