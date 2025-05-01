package com.example.modul6pamrecyclerview.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.modul6pamrecyclerview.database.PetEntity
import com.example.modul6pamrecyclerview.databinding.ItemPetBinding

class PetAdapter(
    private val onDeleteClick: (PetEntity) -> Unit
) : ListAdapter<PetEntity, PetAdapter.PetViewHolder>(DIFF_CALLBACK) {

    inner class PetViewHolder(private val binding: ItemPetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pet: PetEntity) {
            binding.tvName.text = "Nama: ${pet.name}"
            binding.tvType.text = "Jenis: ${pet.type}"
            binding.tvAge.text = "Usia: ${pet.age} tahun"
            binding.tvOwner.text = "Pemilik: ${pet.owner}"

            binding.btnDelete.setOnClickListener {
                onDeleteClick(pet)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val binding = ItemPetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PetEntity>() {
            override fun areItemsTheSame(oldItem: PetEntity, newItem: PetEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PetEntity, newItem: PetEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}
