package com.example.reservarcita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Agendar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agendar)

        val etTime = findViewById<EditText>(R.id.etTime)
        val etDate = findViewById<EditText>(R.id.etDate)
        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val btnAgendar = findViewById<Button>(R.id.btnAgCita)
        etDate.setOnClickListener { showDatePickerDialog() }
        etTime.setOnClickListener { showTimePickerDialog() }

        btnAgendar.setOnClickListener{
            if (etTime.text.isNotEmpty() && etDate.text.isNotEmpty() && etName.text.isNotEmpty() && etAge.text.isNotEmpty()){
                val intent = Intent(this, CancelarCita::class.java)
                intent.putExtra("fecha",etDate.text.toString())
                intent.putExtra("hora",etTime.text.toString())
                intent.putExtra("nombre",etName.text.toString())
                intent.putExtra("edad",etAge.text.toString())
                intent.putExtra("numero", etPhone.text.toString())
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Llenar todos los campos",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")

    }
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val etDate = findViewById<EditText>(R.id.etDate)
        etDate.setText("$day de $month del año $year")
    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(supportFragmentManager, "timePicker")
    }
    private fun onTimeSelected(time: String) {
        val etTime = findViewById<EditText>(R.id.etTime)
        etTime.setText("Cita para las $time")
    }


}

