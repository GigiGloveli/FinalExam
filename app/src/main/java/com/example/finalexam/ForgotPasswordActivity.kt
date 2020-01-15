package com.example.finalexam

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import ge.msda.Finalexam.R
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_update_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()

        backBtn.setOnClickListener {
            finish()
        }

        resetPassword.setOnClickListener {

            val emailii: String = email000.text.toString()

            if (TextUtils.isEmpty(emailii)) {

                Toast.makeText(this, "მიუთითეთ Email", Toast.LENGTH_SHORT).show()

            } else {

                auth.sendPasswordResetEmail(emailii)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "ლინკი გამოსგზავნილია მეილზე", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "მეილზე პაროლის გამოგზავნა ვერ მოხერხდა", Toast.LENGTH_LONG).show()
                        }
                    })

            }

        }

    }


}