package com.example.navarromartinez_jaime_ut7_lab9

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileNotFoundException

class PergaminosActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etContenido: EditText
    private lateinit var btnGuardarInterno: Button
    private lateinit var btnLeerInterno: Button
    private lateinit var btnGuardarExterno: Button
    private lateinit var btnLeerExterno: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pergaminos)

        etNombre = findViewById(R.id.etNombreArchivo)
        etContenido = findViewById(R.id.etContenido)
        btnGuardarInterno = findViewById(R.id.btnGuardarInterno)
        btnLeerInterno = findViewById(R.id.btnLeerInterno)
        btnGuardarExterno = findViewById(R.id.btnGuardarExterno)
        btnLeerExterno = findViewById(R.id.btnLeerExterno)

        // Guardar en memoria interna
        // Hecho por Jaime Navarro
        //21/11/2026
        btnGuardarInterno.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val texto = etContenido.text.toString()

            if (nombre.isNotEmpty()) {
                try {
                    openFileOutput(nombre, Context.MODE_PRIVATE).use { flujo ->
                        flujo.write(texto.toByteArray())
                    }
                    Toast.makeText(this, "Guardado en memoria interna", Toast.LENGTH_SHORT).show()
                    etContenido.text.clear()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error al escribir: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                etNombre.error = "Escribe un nombre de archivo"
            }
        }

        // Leer de memoria interna
        // Hecho por Jaime Navarro
        // 21/11/2026
        btnLeerInterno.setOnClickListener {
            val nombre = etNombre.text.toString().trim()

            if (nombre.isNotEmpty()) {
                try {
                    val textoLeido = openFileInput(nombre).bufferedReader().use { lector ->
                        lector.readText()
                    }
                    etContenido.setText(textoLeido)
                    Toast.makeText(this, "Leído de memoria interna", Toast.LENGTH_SHORT).show()
                } catch (e: FileNotFoundException) {
                    etNombre.error = "Ese archivo no existe"
                    Toast.makeText(this, "Fichero no encontrado", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error al leer: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                etNombre.error = "Escribe el nombre del archivo a leer"
            }
        }

        // Guardar en memoria externa
        // Hecho por Jaime Navarro Martínez
        // 21/11/2026
        btnGuardarExterno.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val texto = etContenido.text.toString()

            if (!esAlmacenamientoExternoEscribible()) {
                Toast.makeText(this, "La memoria externa no está disponible", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (nombre.isNotEmpty()) {
                try {
                    val ruta = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                    val archivo = File(ruta, nombre)
                    archivo.writeText(texto)
                    Toast.makeText(this, "Guardado en memoria externa", Toast.LENGTH_SHORT).show()
                    etContenido.text.clear()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error al escribir: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                etNombre.error = "Escribe un nombre de archivo"
            }
        }

        // Leer de memoria externa
        // Jaime Navarro Martínez
        // 21/11/2026
        btnLeerExterno.setOnClickListener {
            val nombre = etNombre.text.toString().trim()

            if (!esAlmacenamientoExternoLeible()) {
                Toast.makeText(this, "La memoria externa no está disponible", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (nombre.isNotEmpty()) {
                try {
                    val ruta = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                    val archivo = File(ruta, nombre)

                    if (archivo.exists()) {
                        etContenido.setText(archivo.readText())
                        Toast.makeText(this, "Leído de memoria externa", Toast.LENGTH_SHORT).show()
                    } else {
                        etNombre.error = "Ese archivo no existe"
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Error al leer: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                etNombre.error = "Escribe el nombre del archivo a leer"
            }
        }
    }
    private fun esAlmacenamientoExternoEscribible(): Boolean {
        val estado = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == estado
    }

    private fun esAlmacenamientoExternoLeible(): Boolean {
        val estado = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == estado || Environment.MEDIA_MOUNTED_READ_ONLY == estado
    }
}