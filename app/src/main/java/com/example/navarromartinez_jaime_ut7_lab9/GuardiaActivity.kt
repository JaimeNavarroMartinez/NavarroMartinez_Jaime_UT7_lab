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
    }
}