package com.example.reservarcita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.reservarcita.databinding.ActivityCancelarCitaBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CancelarCita : AppCompatActivity() {

    private lateinit var binding: ActivityCancelarCitaBinding

    val database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCancelarCitaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val extras = intent.extras

        val nombre = extras?.getString("nombre")
        val edad = extras?.getString("edad")
        val fecha = extras?.getString("fecha")
        val hora = extras?.getString("hora")
        val numero = extras?.getString("numero")

        binding.tvDate.setText((""+fecha))
        binding.tvTime.setText(""+hora)
        binding.tvName.setText(""+nombre)
        binding.tvAge.setText(""+edad)
        binding.tvNumber.setText(""+numero)


        binding.btnCnCita.setOnClickListener{
            setValue()
        }

    }

    private fun setValue(){
        val myRef = database.getReference("citas").orderByKey().limitToLast(1)
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val id = dataSnapshot.children.last().child("id").getValue(Int::class.java)!!
                val myRef2 = database.getReference("citas").child(id?.plus(1).toString())
                myRef2.setValue(cita(id.plus(1))).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@CancelarCita, "Correcto", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@CancelarCita, "Incorrecto", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@CancelarCita, "Unknown error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun cita(id: Int): Cita {
        val data = Cita(id,binding.tvDate.text.toString(), binding.tvTime.text.toString(), binding.tvName.text.toString(), binding.tvAge.text.toString(), binding.tvNumber.text.toString())
        return data
    }
}