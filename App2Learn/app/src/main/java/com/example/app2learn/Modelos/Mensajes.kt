package com.example.app2learn.Modelos

import com.google.firebase.database.Exclude
import java.sql.Timestamp


class Mensajes (
    var id: String="",
    var mensaje: String="",
    var remitente: String="",
    val fechahora: Any? = null
        ){

    var Miguelito: Boolean = false

}