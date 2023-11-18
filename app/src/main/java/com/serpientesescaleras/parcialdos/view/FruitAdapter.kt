package com.serpientesescaleras.parcialdos.view
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.serpientesescaleras.parcialdos.R
import com.serpientesescaleras.parcialdos.model.Fruit

class FruitAdapter(private val fruits: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fruit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruits[position]
        holder.nameTextView.text = fruit.name

        // Configura el clic del elemento para mostrar el detalle de nutrientes
        holder.itemView.setOnClickListener {
            // Implementa la lógica para mostrar el detalle de nutrientes
        }
    }

    override fun getItemCount(): Int {
        return fruits.size
    }
}