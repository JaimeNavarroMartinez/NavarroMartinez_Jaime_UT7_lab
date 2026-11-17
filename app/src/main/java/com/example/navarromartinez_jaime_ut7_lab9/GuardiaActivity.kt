package com.example.navarromartinez_jaime_ut7_lab9

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class GuardiaActivity : AppCompatActivity() {

    private lateinit var etSoldado: EditText
    private lateinit var spRango: Spinner
    private lateinit var swEstado: SwitchCompat
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guardia)

        etSoldado = findViewById(R.id.etSoldado)
        spRango = findViewById(R.id.spRango)
        swEstado = findViewById(R.id.swEstado)
        btnGuardar = findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val nombre = etSoldado.text.toString().trim()

            if (nombre.isNotEmpty()) {
                val prefs = getSharedPreferences("RegistroGuardia", Context.MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString("KEY_SOLDADO", nombre)
                editor.putInt("KEY_RANGO", spRango.selectedItemPosition)
                editor.putBoolean("KEY_ESTADO", swEstado.isChecked)
                editor.apply()

                etSoldado.text.clear()
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show()
            } else {
                etSoldado.error = "El nombre no puede estar vacío"
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences("RegistroGuardia", Context.MODE_PRIVATE)

        etSoldado.setText(prefs.getString("KEY_SOLDADO", ""))
        spRango.setSelection(prefs.getInt("KEY_RANGO", 0))
        swEstado.isChecked = prefs.getBoolean("KEY_ESTADO", false)
    }}