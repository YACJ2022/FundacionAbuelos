package com.example.contactos.GestioDeUsuarios.Enfermeras


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactos.GestioDeUsuarios.Empleados.Empleado
import com.example.contactos.GestioDeUsuarios.Empleados.EmpleadoPersonalizado
import com.example.contactos.GestioDeUsuarios.pacientes.PacientesPersonalizado
import com.fup.sisexper.fundacionabuelos.R
import com.fup.sisexper.fundacionabuelos.ResourceForms.Formularios.MainEnfermer

class EnfermeroPersonalizado :  AppCompatActivity() {
    private lateinit var listas: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_enfermeras)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.enfermerasview)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnEmpleado = findViewById<Button>(R.id.btnEmpleado)
        val btnEnfermeros = findViewById<Button>(R.id.btnEnfermeros)
        val btnPaciente = findViewById<Button>(R.id.btnPaciente)

        val add = findViewById<ImageView>(R.id.addEnfermero)
        listas = findViewById(R.id.listaEnfermera)
        val enfermeros = listOf(
            Enfermero(
                nombre = "La Esperanza",
                cargo = "Ana Pérez",
                imagen = R.drawable.ic_enfermeras
            ),

            Enfermero(
                nombre = "Residencia Los Pinos",
                cargo = "Juan Gómez",
                imagen = R.drawable.ic_enfermeras
            ),

            Enfermero(
                nombre = "Residencia El Bosque",
                cargo = "Luisa Rodríguez",
                imagen = R.drawable.ic_enfermeras
            ),

            )

        add.setOnClickListener {
            Toast.makeText(this,  "Agregar nuevo Empleado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainEnfermer::class.java)
            startActivity(intent)
        }

        // Agregar interactividad a los botones
        btnEmpleado.setOnClickListener {
            val intent = Intent(this, EmpleadoPersonalizado::class.java)
            startActivity(intent)

        }

        btnEnfermeros.setOnClickListener {
            val intent = Intent(this, EnfermeroPersonalizado::class.java)
            startActivity(intent)
        }

        btnPaciente.setOnClickListener {
            Toast.makeText(this, "Vista de pacientes seleccionada", Toast.LENGTH_SHORT).show()
            //Navegar a PacientesActivity
            val intent = Intent(this, PacientesPersonalizado::class.java)
            startActivity(intent)
        }

        val adaptor = EnfermeroAdaptador(this, enfermeros)
        listas.adapter= adaptor
    }
}
