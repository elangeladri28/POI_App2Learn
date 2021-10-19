package com.example.app2learn

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.app2learn.Adaptadores.chatAdapter
import com.example.app2learn.Modelos.Mensajes
import com.google.firebase.database.*


class MensajesGrupales : AppCompatActivity() {


    private val messageList = mutableListOf<Mensajes>()
    private lateinit var chatAdap: chatAdapter
    private lateinit var rvChats: RecyclerView
    private lateinit var username: String
    private val database = FirebaseDatabase.getInstance()
    private val chatRef = database.getReference("chats")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensaje)

        rvChats = findViewById(R.id.RV_Mensajes)

        chatAdap = chatAdapter(messageList)
        rvChats.adapter = chatAdap

        username = intent.getStringExtra("username") ?: "Sin nombre"

        findViewById<ImageView>(R.id.btn_Send).setOnClickListener {

            val message = findViewById<EditText>(R.id.txtMessage).text.toString()
            if (message.isNotEmpty()) {

                val msg = Mensajes("", message, username, ServerValue.TIMESTAMP)
                sendMessage(msg)
            }
        }

        readMessages()
    }

    private fun sendMessage(message: Mensajes) {

        val firebaseMsg = chatRef.push()
        message.id = firebaseMsg.key ?: ""

        firebaseMsg.setValue(message)
    }

    private fun readMessages() {

        chatRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                messageList.clear()

                for (snap in snapshot.children) {

                    val currentMessage: Mensajes = snap.getValue(Mensajes::class.java) as Mensajes

                    if (currentMessage.mensaje == username) {
                        currentMessage.Miguelito = true
                    }

                    messageList.add(currentMessage)
                }

                if (messageList.size > 0 ) {
                    chatAdap.notifyDataSetChanged()
                    rvChats.smoothScrollToPosition(messageList.size - 1)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}