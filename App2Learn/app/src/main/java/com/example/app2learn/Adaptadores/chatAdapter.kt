package com.example.app2learn.Adaptadores

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app2learn.R
import com.example.app2learn.Modelos.Mensajes
import java.text.SimpleDateFormat
import java.util.*


class chatAdapter (private val messageList: List<Mensajes>) :
        RecyclerView.Adapter<chatAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.mensajes_derecha, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(messageList[position])
    }

    override fun getItemCount(): Int = messageList.size

    class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(message: Mensajes) {

            itemView.findViewById<TextView>(R.id.txtMessageRemitente).text = message.remitente
            itemView.findViewById<TextView>(R.id.txtMessage).text = message.mensaje
            val tvDate = itemView.findViewById<TextView>(R.id.txtDateMessage)
            val date = message.fechahora as Long
            tvDate.text = SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale("es", "MX"))
                .format(date)

            val messageContainer = itemView.findViewById<LinearLayout>(R.id.LL_MessageContainer)
            val params = messageContainer.layoutParams
            if (message.Miguelito) {
                val newParams = FrameLayout.LayoutParams(params.width, params.height, Gravity.END)
                messageContainer.layoutParams = newParams
                messageContainer.setBackgroundColor(Color.CYAN)
            } else {
                val newParams = FrameLayout.LayoutParams(params.width, params.height, Gravity.START)
                messageContainer.layoutParams = newParams
                messageContainer.setBackgroundColor(Color.GRAY)
            }
        }
    }
}