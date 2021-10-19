package com.example.app2learn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_login).setOnClickListener {


            val username = findViewById<EditText>(R.id.txt_Username).text.toString()
            if(username.isNotEmpty()){
                val intent = Intent(this, MensajesGrupales::class.java)
                intent.putExtra("username", username)
                startActivity(intent)



            }

            else{

            }


        }
    }
}