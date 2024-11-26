package com.example.contactos.GestioDeUsuarios.pacientes


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
import com.example.contactos.GestioDeUsuarios.Empleados.EmpleadoPersonalizado
import com.example.contactos.GestioDeUsuarios.Enfermeras.EnfermeroPersonalizado
import com.fup.sisexper.fundacionabuelos.R
import com.fup.sisexper.fundacionabuelos.ResourceForms.Formularios.MainInfoAbuelos

class PacientesPersonalizado :  AppCompatActivity() {
    private lateinit var listas: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_pacientes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pacienteview)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnEmpleado = findViewById<Button>(R.id.btnEmpleado)
        val btnEnfermeros = findViewById<Button>(R.id.btnEnfermeros)
        val btnPaciente = findViewById<Button>(R.id.btnPaciente)

        val add = findViewById<ImageView>(R.id.addPaciente)
        listas = findViewById(R.id.listaPasientes)
        val Pacientes = listOf(
            Paciente(
                nombre = "La Esperanza",
                habitacion = "Ana Pérez",
                imagen = R.drawable.ic_pacientes
            ),

            Paciente(
                nombre = "Residencia Los Pinos",
                habitacion = "Juan Gómez",
                imagen = R.drawable.ic_enfermeras
            ),

            Paciente(
                nombre = "Residencia El Bosque",
                habitacion = "Luisa Rodríguez",
                imagen = R.drawable.ic_enfermeras
            ),

            )

        add.setOnClickListener {
            Toast.makeText(this,  "Agregar nuevo Empleado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainInfoAbuelos::class.java)
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

        val adaptor = PacienteAdaptador(this, Pacientes)
        listas.adapter= adaptor
    }
}
