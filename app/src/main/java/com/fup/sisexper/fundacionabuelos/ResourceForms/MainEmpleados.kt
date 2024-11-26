package com.fup.sisexper.fundacionabuelos.ResourceForms

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fup.sisexper.fundacionabuelos.R

class MainEmpleados : AppCompatActivity() {
    private lateinit var datePickerFechaNacimiento: EditText
    private lateinit var autoCompleteTxt: AutoCompleteTextView
    private lateinit var adapterItems: ArrayAdapter<String>
    private lateinit var buttonCamera: ImageView

    // Configurar el launcher para la cámara
    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // Obtener la imagen capturada
            val photo = result.data?.extras?.get("data") as Bitmap
            // Mostrar la imagen en el ImageView
            buttonCamera.setImageBitmap(photo)

            // Guardar la imagen en el almacenamiento
            saveImageToGallery(photo)
        } else {
            Toast.makeText(this, "No se capturó ninguna imagen", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_empleados)

        // Inicializar la funcionalidad de la cámara
        buttonCamera = findViewById(R.id.screenImage)
        buttonCamera.setOnClickListener {
            // Crear un intent para abrir la cámara
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (cameraIntent.resolveActivity(packageManager) != null) {
                // Usar el launcher para manejar la respuesta
                cameraLauncher.launch(cameraIntent)
            } else {
                Toast.makeText(this, "No se encontró una app de cámara", Toast.LENGTH_SHORT).show()
            }
        }

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

    // Función para guardar la imagen en la galería
    private fun saveImageToGallery(photo: Bitmap) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "captured_image_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/FundacionAbuelos") // Directorio personalizado
        }

        val contentResolver = contentResolver
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        if (uri != null) {
            contentResolver.openOutputStream(uri)?.use { outputStream ->
                val success = photo.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                if (success) {
                    Toast.makeText(this, "Imagen guardada en la galería", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show()
                }
            } ?: run {
                Toast.makeText(this, "No se pudo abrir el flujo de salida", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show()
        }
    }
}
