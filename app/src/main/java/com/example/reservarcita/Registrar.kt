package com.example.reservarcita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class Registrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarse)
        val etCorreo = findViewById<EditText>(R.id.etCorreoR)
        val etContraseña = findViewById<EditText>(R.id.etContraseñaR)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)


        btnRegistrar.setOnClickListener {
            if (etCorreo.text.isNotEmpty() && etContraseña.text.isNotEmpty() && etNombre.text.isNotEmpty() && etTelefono.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(etCorreo.text.toString(),
                        etContraseña.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            startActivity(Intent(this, MainActivity::class.java))
                        }else{
                            showAlert("Error al registrar", "Usuario existente")
                        }
                    }
            } else {
                showAlert("Campos vacios", "Llenar los campos")
            }
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

}