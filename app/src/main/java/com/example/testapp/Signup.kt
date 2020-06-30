package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*
import kotlin.math.sign
import kotlin.time.measureTimedValue

class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance();

        signup_btn.setOnClickListener {

           signUpUser()

        }
    }
   private fun signUpUser() {
       if (Email1.text.toString().isEmpty()) {
           Email1.error = "Please enter Email"
           username.requestFocus()
           return

       }
       if (!Patterns.EMAIL_ADDRESS.matcher(Email1.text.toString()).matches()) {
           Email1.error = "Please enter valid Email"
           Email1.requestFocus()
           return

       }
       if (Password1.text.toString().isEmpty()) {
           Password1.error = "Please enter Password"
           username.requestFocus()
           return
       }

       auth.signInWithEmailAndPassword(Email1.text.toString(), Password1.text.toString())
           .addOnCompleteListener(this) { task ->
               if (task.isSuccessful) {
                   val user = auth.currentUser
                   startActivity(Intent(this, Home::class.java))
                   Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show()
               } else {
                   Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT
                   ).show()
               }

           }
   }

}