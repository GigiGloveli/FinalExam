package com.example.finalexam

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalexam.finalexam.db
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ge.msda.Finalexam.R
import kotlinx.android.synthetic.main.activity_update_password.*

class UpdateinfoActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbs: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)

        auth = FirebaseAuth.getInstance()
        dbs = FirebaseDatabase.getInstance().getReference("db")

        backBtn2.setOnClickListener {
            finish()
        }

        changePassword.setOnClickListener {
            val email: String = emaili.text.toString()
            val password: String = password.text.toString()



            if (!TextUtils.isEmpty(password)) {
                auth.currentUser?.updatePassword(password)?.addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Toast.makeText(
                            this@UpdateinfoActivity,
                            "პაროლის შეცვლა ვერ მოხერხდა",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            } else {
                Toast.makeText(
                    this@UpdateinfoActivity,
                    "ოპრაცია წარმატებით დასრულდა",
                    Toast.LENGTH_LONG
                ).show()

            }

            if (!TextUtils.isEmpty(email)) {

                auth.currentUser?.updateEmail(email)?.addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Toast.makeText(
                            this@UpdateinfoActivity,
                            "Email-ის შეცვლა ვერ მოხერხდა",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }

            } else {
                Toast.makeText(
                    this@UpdateinfoActivity,
                    "ოპრაცია წარმატებით დასრულდა",
                    Toast.LENGTH_LONG
                ).show()
            }

        }

    }


}