package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_signup.*
import kotlin.math.sign
import kotlin.time.measureTimedValue


class Signup<Menu> : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase: DatabaseReference

    interface DatabaseReference {

    }

    var user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val signup=findViewById<View>(R.id.signup_btn) as Button
       // mDatabase=FirebaseDatabase.getInstance().getReference("Name")

        mDatabase = Firebase.database.reference
        signup_btn.setOnClickListener(View.OnClickListener {
            signupUser()
        })

    }
    private fun signupUser(){
        val email1= findViewById<View>(R.id.Email1) as EditText
        val password1 = findViewById<View>(R.id.Password1) as EditText
        val usernameTxt= findViewById<View>(R.id.username) as EditText


        val email = email1.text.toString()
        val password =password1.text.toString()
        val username =username.text.toString()
        if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,
                OnCompleteListener {task->
                    if(task.isSuccessful){
                        val user = mAuth.currentUser
                        val uid= user

                    }else{
                        Toast.makeText(this,"Error:(",Toast.LENGTH_LONG).show()
                    }
                })

        }else{
            Toast.makeText(this, "Please enter creditional:(",Toast.LENGTH_SHORT).show()
        }

    }
}




