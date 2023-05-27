package com.example.reservarcita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Inicio : AppCompatActivity() {

    lateinit var BotonC: Button
    lateinit var BotonS: Button
    lateinit var BotonL: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)


        BotonC=findViewById<Button>(R.id.btnCita)
        BotonC.setOnClickListener {
            val inicio = Intent(this, Agendar::class.java)
            startActivity(inicio)
        }

        BotonL=findViewById<Button>(R.id.btnLista)
        BotonL.setOnClickListener {
            val listaCitas = Intent(this, ListaCitas::class.java)
            startActivity(listaCitas)
        }

        BotonS=findViewById<Button>(R.id.btnServicios)
        BotonS.setOnClickListener {
            val servicios = Intent(this, Servicios::class.java)
            startActivity(servicios)
        }

    }

}






