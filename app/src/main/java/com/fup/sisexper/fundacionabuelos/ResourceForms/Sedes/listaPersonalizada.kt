package com.example.contactos.Sedes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactos.GestioDeUsuarios.Empleados.EmpleadoPersonalizado
import com.fup.sisexper.fundacionabuelos.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.fup.sisexper.fundacionabuelos.ResourceForms.MainActivity
import com.fup.sisexper.fundacionabuelos.ResourceForms.Service.RetroFitClient

class listaPersonalizada : AppCompatActivity() {
    private lateinit var listado: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_sedes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sedesview)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val add = findViewById<ImageView>(R.id.addButton)

        add.setOnClickListener {
            Toast.makeText(this,  "Agregar nueva sede", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity ::class.java)
            startActivity(intent)
        }

        listado = findViewById(R.id.lista)
        fetchSedes()
    }

    private fun fetchSedes() {
        val apiService = RetroFitClient.apiService

        apiService.obtenerSede().enqueue(object : Callback<List<item>> {
            override fun onResponse(call: Call<List<item>>, response: Response<List<item>>) {
                if (response.isSuccessful) {
                    val sedes = response.body() ?: emptyList()
                    val adaptador = SedeAdaptador(this@listaPersonalizada, sedes)
                    listado.adapter = adaptador

                    // Configurar acción al hacer clic en un ítem
                    listado.setOnItemClickListener { _, _, position, _ ->
                        val selectedSede = sedes[position]
                        val intent =
                            Intent(this@listaPersonalizada, EmpleadoPersonalizado::class.java)
                        intent.putExtra("nombre", selectedSede.nombre)
                        intent.putExtra("encargado", selectedSede.nombreEncargado)
                        intent.putExtra("imagen", "drawable/fud_prot.png")
                        startActivity(intent)

                    }

                } else {
                    Toast.makeText(
                        this@listaPersonalizada,
                        "Error al cargar sedes",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<item>>, t: Throwable) {
                Log.e("ListaPersonalizada", "Error: ${t.message}")
                Toast.makeText(this@listaPersonalizada, "Error de conexión", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}