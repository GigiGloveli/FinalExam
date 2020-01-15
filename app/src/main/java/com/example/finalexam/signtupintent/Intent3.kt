package com.example.finalexam.signtupintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import ge.msda.Finalexam.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.jar.Attributes

class Intent3 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        nextbtn.setOnClickListener {

            val email: String = emaill.text.toString()
            val password: String = password.text.toString()
            val verifypass = passwordver.text.toString()


            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

                Toast.makeText(this, "გთხოვთ შეავსოთ ყველა ველი", Toast.LENGTH_SHORT).show()

            } else {
              if (password == verifypass) {
                  auth.createUserWithEmailAndPassword(email, password)
                      .addOnCompleteListener(this, OnCompleteListener { task ->

                          if (task.isSuccessful) {

                              auth.signInWithEmailAndPassword(email, password)
                                  .addOnCompleteListener(this, OnCompleteListener { task ->
                                      if (task.isSuccessful) {
                                          val intent = Intent(this, Intent1::class.java)
                                          startActivity(intent)
                                          finish()

                                      } else {

                                          Toast.makeText(
                                              this,
                                              "შეიქმნა მარა ვერშევიდა !!",
                                              Toast.LENGTH_SHORT
                                          ).show()
                                      }
                                  })
                          } else {

                              Toast.makeText(this, "ვერ შეიქმნა !!", Toast.LENGTH_SHORT).show()

                          }
                      })
              }else{

                  Toast.makeText(this,"პაროლები არ ემთხვევა !!",Toast.LENGTH_SHORT).show()
              }

            }

        }


    }


}