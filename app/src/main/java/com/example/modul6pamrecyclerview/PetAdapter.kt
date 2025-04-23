package com.example.modul6pamrecyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

class PetAdapter(
    private val pets: ArrayList<PetModel>,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

    inner class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvType = itemView.findViewById<TextView>(R.id.tvType)
        val tvAge = itemView.findViewById<TextView>(R.id.tvAge)
        val tvOwner = itemView.findViewById<TextView>(R.id.tvOwner)
        val btnDelete = itemView.findViewById<Button>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pet, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet = pets[position]
        holder.tvName.text = "Nama: ${pet.name}"
        holder.tvType.text = "Jenis: ${pet.type}"
        holder.tvAge.text = "Usia: ${pet.age} tahun"
        holder.tvOwner.text = "Pemilik: ${pet.owner}"

        holder.btnDelete.setOnClickListener {
            onDelete(position)
        }
    }

    override fun getItemCount() = pets.size
}
