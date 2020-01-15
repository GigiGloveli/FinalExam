package com.example.finalexam


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalexam.signtupintent.Intent3
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import ge.msda.Finalexam.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {

            val email: String = email2.text.toString()
            val password: String = password.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

                Toast.makeText(this@LoginActivity, "შეავსეთ ველები", Toast.LENGTH_LONG)
                    .show()

            } else {

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, FirstpageActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "შეცდომა !! ", Toast.LENGTH_LONG)
                                .show()
                        }
                    })

            }
        }

        signUpBtn.setOnClickListener {
            val intent = Intent(this, Intent3::class.java)
            startActivity(intent)
            finish()
        }

        resetPassword2.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

    }


}