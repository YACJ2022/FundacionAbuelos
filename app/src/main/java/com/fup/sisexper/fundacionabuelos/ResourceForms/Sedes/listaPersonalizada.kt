package com.example.contactos.Sedes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactos.GestioDeUsuarios.Empleados.EmpleadoPersonalizado
import com.fup.sisexper.fundacionabuelos.R
import com.fup.sisexper.fundacionabuelos.ResourceForms.MainActivity

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
        listado = findViewById(R.id.lista)
        val items = listOf(
            item(
                nombre = "Residencia La Esperanza",
                encargado = "Ana Pérez",
                imagen = R.drawable.fud_prot
            ),

            item(
                nombre = "Residencia Los Pinos",
                encargado = "Juan Gómez",
                imagen = R.drawable.fud_prot
            ),

            item(
                nombre = "Residencia El Bosque",
                encargado = "Luisa Rodríguez",
                imagen = R.drawable.fud_prot
            ),

        )

        add.setOnClickListener {
            Toast.makeText(this,  "Agregar nueva sede", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity ::class.java)
            startActivity(intent)
        }
        val adaptador = SedeAdaptador(this, items)
        listado.adapter= adaptador

        listado.setOnItemClickListener { _, _, position, _ ->
            val selectedSede = items[position]
            val intent = Intent(this, EmpleadoPersonalizado::class.java)
            intent.putExtra("nombre", selectedSede.nombre)
            intent.putExtra("encargado", selectedSede.encargado)
            startActivity(intent)
        }
    }
}