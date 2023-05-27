package com.example.reservarcita

import android.widget.Button


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import java.io.File

class MainActivity : AppCompatActivity() {

    private var email: String = "admin123@gmail.com"
    private var pass: String ="123456"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val iniciarSesion = findViewById<Button>(R.id.btnEntrar)
        val textEmailAddress = findViewById<EditText>(R.id.etEmail)
        val editTextPassword = findViewById<EditText>(R.id.etContraseña)

        iniciarSesion.setOnClickListener {
            if (textEmailAddress.text.isNotEmpty() && editTextPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(textEmailAddress.text.toString(),
                        editTextPassword.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            startActivity(Intent(this, Inicio::class.java))
                        }else{
                            showAlert("Error al iniciar sesion", "Usuario o contraseña incorrectos")
                        }
                    }
            }else{
                showAlert("Campos vacios", "Llenar los campos")
            }
        }




        //Setup
        setup()

    }
    //Función setup
    private fun setup(){
        val iniciarSesion = findViewById<Button>(R.id.btnEntrar)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarse)
        val textEmailAddress = findViewById<EditText>(R.id.etEmail)
        val editTextPassword = findViewById<EditText>(R.id.etContraseña)


        iniciarSesion.setOnClickListener {
            if (textEmailAddress.text.isNotEmpty() && editTextPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(textEmailAddress.text.toString(),
                        editTextPassword.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            crearArchivo(textEmailAddress.text.toString())
                            startActivity(Intent(this, Inicio::class.java))
                        }else{
                            showAlert("Error al iniciar sesion", "Usuario o contraseña incorrectos")
                        }
                    }
            }else{
                showAlert("Campos vacios", "Llenar los campos")
            }
        }

        btnRegistrar.setOnClickListener{
            startActivity(Intent(this, Registrar::class.java))

        }

    }
    private fun showAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Guardado de archivos
    fun crearArchivo(correo: String) {
        try {
            val nombreArchivo = "correo.txt"
            val archivo = File(applicationContext.getExternalFilesDir(null), nombreArchivo)
            archivo.createNewFile()
            archivo.writeText(correo)
            Toast.makeText(this, "Archivo creado.", Toast.LENGTH_SHORT).show()
        } catch (ex: Exception) {
            Toast.makeText(this, "Error al escribir archivo. $ex", Toast.LENGTH_SHORT).show()
        }
    }

}