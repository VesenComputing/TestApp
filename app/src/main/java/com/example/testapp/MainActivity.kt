package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*

class MainActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Sign_up.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
            finish()
        }


        login_btn.setOnClickListener {
            OneC()
        }
    }

   private fun OneC(){

        LoginToFireBase(Email.text.toString(),Password.text.toString())

    }

   private fun LoginToFireBase(email:String,password:String){

        mAuth!!.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->

                if (task.isSuccessful){
                    startActivity(Intent(this, Home::class.java))
                    Toast.makeText(applicationContext,"Successful login",Toast.LENGTH_LONG).show()
                }
                else
                {
                    Toast.makeText(this,"Login UnSuccessful",Toast.LENGTH_LONG).show()
                }
            }
    }
}