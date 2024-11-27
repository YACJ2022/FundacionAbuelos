package com.fup.sisexper.fundacionabuelos.ResourceForms

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactos.Sedes.item
import com.fup.sisexper.fundacionabuelos.R
import com.fup.sisexper.fundacionabuelos.ResourceForms.Service.RetroFitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // Declarar variables globales para los campos de entrada
    private lateinit var nombreSede: EditText
    private lateinit var numeroRegistro: EditText
    private lateinit var ciudad: EditText
    private lateinit var barrio: EditText
    private lateinit var direccion: EditText
    private lateinit var telefono: EditText
    private lateinit var nombreEncargado: EditText
    private lateinit var correoElectronico: EditText
    private lateinit var capacidad: EditText
    private lateinit var numeroHabitaciones: EditText
    private lateinit var habitacionesDisponibles: EditText
    private lateinit var btnAgregar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.registro_sede)

        // Ajustar el diseño para evitar superposición con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar los campos de entrada y botones
        inicializarComponentes()

        // Acción al presionar "Agregar"
        btnAgregar.setOnClickListener {
            agregarNuevaSede()
        }

        // Acción al presionar "Cancelar"
        btnCancelar.setOnClickListener {
            finish() // Cierra la actividad
        }
    }

    private fun inicializarComponentes() {
        // Inicialización de los campos de entrada
        nombreSede = findViewById(R.id.etNombreSede)
        numeroRegistro = findViewById(R.id.etNumeroRegistro)
        ciudad = findViewById(R.id.etCiudad)
        barrio = findViewById(R.id.etBarrio)
        direccion = findViewById(R.id.etDireccion)
        telefono = findViewById(R.id.etTelefono)
        nombreEncargado = findViewById(R.id.etNombreEncargado)
        correoElectronico = findViewById(R.id.etCorreoElectronico)
        capacidad = findViewById(R.id.etCapacidad)
        numeroHabitaciones = findViewById(R.id.etNumeroHabitaciones)
        habitacionesDisponibles = findViewById(R.id.etHabitacionesDisponibles)

        // Inicialización de los botones
        btnAgregar = findViewById(R.id.btnAgregar)
        btnCancelar = findViewById(R.id.btnCancelar)
    }

    private fun agregarNuevaSede() {
        // Validar los campos antes de continuar
        if (validarCampos()) {
            val nuevaSede = item(
                nombre = nombreSede.text.toString(),
                numeroRegistro = numeroRegistro.text.toString().toInt(),
                ciudad = ciudad.text.toString(),
                barrio = barrio.text.toString(),
                direccion = direccion.text.toString(),
                telefono = telefono.text.toString(),
                nombreEncargado = nombreEncargado.text.toString(),
                correoElectronico = correoElectronico.text.toString(),
                capacidad = capacidad.text.toString().toInt(),
                numeroHabitaciones = numeroHabitaciones.text.toString().toInt(),
                habitacionesDisponibles = habitacionesDisponibles.text.toString().toInt(),
                imagen = R.drawable.ic_enfermeras // Ajusta según tus necesidades
            )

            // Llamar a la API para insertar la sede
            insertarSedeAPI(nuevaSede)
        } else {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validarCampos(): Boolean {
        return nombreSede.text.isNotEmpty() &&
                numeroRegistro.text.isNotEmpty() &&
                ciudad.text.isNotEmpty() &&
                barrio.text.isNotEmpty() &&
                direccion.text.isNotEmpty() &&
                telefono.text.isNotEmpty() &&
                nombreEncargado.text.isNotEmpty() &&
                correoElectronico.text.isNotEmpty() &&
                capacidad.text.isNotEmpty() &&
                numeroHabitaciones.text.isNotEmpty() &&
                habitacionesDisponibles.text.isNotEmpty()
    }

    private fun insertarSedeAPI(sede: item) {
        val call = RetroFitClient.apiService.insertarSede(sede)
        call.enqueue(object : Callback<item> {
            override fun onResponse(call: Call<item>, response: Response<item>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Sede insertada correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish() // Cierra la actividad
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Error al insertar sede: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<item>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Error en la conexión: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
