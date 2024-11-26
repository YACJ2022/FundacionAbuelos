package com.example.contactos.GestioDeUsuarios.Empleados


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
import com.example.contactos.GestioDeUsuarios.Enfermeras.EnfermeroPersonalizado
import com.example.contactos.GestioDeUsuarios.pacientes.PacientesPersonalizado
import com.fup.sisexper.fundacionabuelos.R
import com.fup.sisexper.fundacionabuelos.ResourceForms.Formularios.MainEmpleados

class EmpleadoPersonalizado :  AppCompatActivity() {
    private lateinit var lista: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_empleados)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.empleadoView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnEmpleado = findViewById<Button>(R.id.btnEmpleado)
        val btnEnfermeros = findViewById<Button>(R.id.btnEnfermeros)
        val btnPaciente = findViewById<Button>(R.id.btnPaciente)



        val add = findViewById<ImageView>(R.id.addEmpleado)
        lista = findViewById(R.id.listaEmpleados)
        val Empleados = listOf(
            Empleado(
                nombre = "Residencia La Esperanza",
                cargo = "Ana Pérez",
                imagen = R.drawable.ic_enfermeras
            ),

            Empleado(
                nombre = "Residencia Los Pinos",
                cargo = "Juan Gómez",
                imagen = R.drawable.ic_enfermeras
            ),

            Empleado(
                nombre = "Residencia El Bosque",
                cargo = "Luisa Rodríguez",
                imagen = R.drawable.ic_enfermeras
            ),

            )

        add.setOnClickListener {
            Toast.makeText(this,  "Agregar nuevo Empleado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainEmpleados::class.java)
            startActivity(intent)
        }
        val adaptor = EmpleadoAdaptador(this, Empleados)
        lista.adapter= adaptor

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
        

    }
}
