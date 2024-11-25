package com.fup.sisexper.fundacionabuelos

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainEmpleados : AppCompatActivity() {
    private lateinit var datePickerFechaNacimiento: EditText
    private lateinit var autoCompleteTxt: AutoCompleteTextView
    private lateinit var adapterItems: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_empleados)

        // Inicializar el DropDown
        val items = arrayOf("Enfermero General", "Enfermero Asistente", "Jefe de Enfermería", "Coordinador de Enfermería", "Supervisor de Enfermería")
        autoCompleteTxt = findViewById(R.id.auto_complete_txt)
        adapterItems = ArrayAdapter(this, R.layout.list_item_form, items)
        autoCompleteTxt.setAdapter(adapterItems)

        // Listener para ítems seleccionados
        autoCompleteTxt.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "Item: $selectedItem", Toast.LENGTH_SHORT).show()
        }

        // Inicializar el DatePicker
        datePickerFechaNacimiento = findViewById(R.id.DatePickerFechaNacimiento)
        datePickerFechaNacimiento.setOnClickListener { showDatePickerDialog() }

        // Configurar ajustes de padding para bordes de ventana (EdgeToEdge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainEmpleados)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Función para mostrar el DatePicker
    private fun showDatePickerDialog() {
        val datePicker = DatePicker { day, month, year ->
            onDateSelect(day, month, year)
        }
        datePicker.show(supportFragmentManager, "DatePicker")
    }

    // Función para manejar la fecha seleccionada
    private fun onDateSelect(day: Int, month: Int, year: Int) {
        datePickerFechaNacimiento.setText("$day/$month/$year")
    }
}
