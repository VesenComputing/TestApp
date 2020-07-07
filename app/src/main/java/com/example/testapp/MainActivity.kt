package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
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

           val Email= findViewById<android.view.View>(R.id.Email1) as EditText
           val Password = findViewById<android.view.View>(R.id.Password1) as EditText



           val email =Email.text.toString()
           val password =Password.text.toString()





        if (!email.isEmpty() && !password.isEmpty()) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful){
                    //                  sign in success update ui
                    Log.d("success", "create user with email is successful")


                    Toast.makeText(this, "Sucess", Toast.LENGTH_LONG).show()



//                        updateUi(user)
                }else {
                    Log.w("failure", "Create user with email failed")

                    Toast.makeText(baseContext, "Authentication failed." + task.exception!!.message,
                        Toast.LENGTH_SHORT).show()
//                        updateUi(null)
                }
            }

    }else {
        Toast.makeText(this,"Please fill up the Credentials :|", Toast.LENGTH_LONG).show()
    }


}
}