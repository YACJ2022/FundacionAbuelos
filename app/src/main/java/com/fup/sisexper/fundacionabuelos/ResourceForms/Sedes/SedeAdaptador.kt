package com.example.contactos.Sedes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

import com.fup.sisexper.fundacionabuelos.R


class SedeAdaptador(context: Context, private val items: List<item>):
    ArrayAdapter<item>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_layout,
            parent, false
        )
        val item = items[position]
        //Asignacion dde los datos a variables
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val nameTextView = view.findViewById<TextView>(R.id.nameTextView)
        val managerTextView = view.findViewById<TextView>(R.id.managerTextView)
        val editIcon = view.findViewById<ImageView>(R.id.ic_edit)
        val deleteIcon = view.findViewById<ImageView>(R.id.ic_delete)
        val viewIcon = view.findViewById<ImageView>(R.id.ic_view)


        nameTextView.text = item.nombre
//        managerTextView.text = item.nombreEncargado

        Glide.with(context)
            .load(item.imagen)
            .transform(CircleCrop())
            .into(imageView)

        editIcon.setOnClickListener {
            Toast.makeText(context, "Editar ${item.nombre}", Toast.LENGTH_SHORT).show()
            // Implementar lógica de edición aquí
        }

        deleteIcon.setOnClickListener {
            Toast.makeText(context, "Eliminar ${item.nombre}", Toast.LENGTH_SHORT).show()
            // Implementar lógica de eliminación aquí
        }

        viewIcon.setOnClickListener {
            Toast.makeText(context, "ver ${item.nombre}", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}