package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.view.View
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*

class Signup<Menu> : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var database: DatabaseReference

    private var fUserID: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        signup_btn.setOnClickListener {
            writeNewUser()
        }




    }

    private fun writeNewUser() {
        val email1= findViewById<android.view.View>(R.id.Email1) as EditText
        val password1 = findViewById<android.view.View>(R.id.Password1) as EditText
        val usernameTxt= findViewById<android.view.View>(R.id.username) as EditText


        val email = email1.text.toString()
        val password =password1.text.toString()
        val username =username.text.toString()


        if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful){
                        //                  sign in success update ui
                        Log.d("success", "create user with email is successful")

                        fUserID = auth.currentUser!!.uid
                        database = FirebaseDatabase.getInstance().reference.child("dbfire").child(fUserID)
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
