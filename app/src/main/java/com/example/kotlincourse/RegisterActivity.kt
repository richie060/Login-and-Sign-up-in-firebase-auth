package com.example.kotlincourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

      lateinit var auth: FirebaseAuth
      var databaseReference: DatabaseReference?= null
      var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")
        register()
    }

    private  fun  register(){
        registerButton.setOnClickListener {
            if (TextUtils.isEmpty(firstName.text.toString())){
                firstName.setError("Please enter first name")
                return@setOnClickListener
            }else  if (TextUtils.isEmpty(lastName.text.toString())) {
                lastName.setError("Please enter last name")
                return@setOnClickListener
            }else  if (TextUtils.isEmpty(password.text.toString())) {
                password.setError("Please enter the password")
                return@setOnClickListener
            }

                auth.createUserWithEmailAndPassword(usernameInput.text.toString(), password.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            val currentUser = auth.currentUser
                            val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                            currentUserDb?.child("firstname")?.setValue(firstName.text.toString())
                            currentUserDb?.child("lastname")?.setValue(lastName.text.toString())
                            Toast.makeText(this@RegisterActivity,"Registration  Successfull",Toast.LENGTH_LONG).show()
                            finish()
                        }
                        else
                        {
                            Toast.makeText(this@RegisterActivity,"Registration failed please try again",Toast.LENGTH_LONG).show()
                        }
                    }


        }
    }
}