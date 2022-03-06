package com.example.kotlincourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login2.*

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        auth = FirebaseAuth.getInstance()
        Login()
    }

    private fun Login(){
        loginButton.setOnClickListener {
            if (TextUtils.isEmpty(usernameInput.text.toString())){
                 usernameInput.setError("Please enter Username")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(passwordInput.text.toString()))

            {
                passwordInput.setError("Please Enter password")
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(usernameInput.text.toString(), passwordInput.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this@LoginActivity,Home_Profile::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@LoginActivity, "Login failed please try again", Toast.LENGTH_LONG).show()


                    }
                }
        }
    }
}