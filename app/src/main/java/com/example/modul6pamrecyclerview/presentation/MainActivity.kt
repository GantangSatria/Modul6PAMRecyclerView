package com.example.modul6pamrecyclerview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul6pamrecyclerview.database.PetDB
import com.example.modul6pamrecyclerview.databinding.ActivityMainBinding
import com.example.modul6pamrecyclerview.presentation.PetAdapter
import com.example.modul6pamrecyclerview.presentation.PetViewModel
import com.example.modul6pamrecyclerview.presentation.PetViewModelFactory
import com.example.modul6pamrecyclerview.repository.PetRepository
import com.example.modul6pamrecyclerview.database.PetEntity
import com.example.modul6pamrecyclerview.presentation.AddPetActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PetAdapter

    private val viewModel: PetViewModel by viewModels {
        PetViewModelFactory(PetRepository(PetDB.getInstance(this).petDao()))
    }

    private val addPetLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val name = data?.getStringExtra("name") ?: return@registerForActivityResult
            val type = data.getStringExtra("type") ?: return@registerForActivityResult
            val age = data.getIntExtra("age", 0)
            val owner = data.getStringExtra("owner") ?: return@registerForActivityResult

            val newPet = PetEntity(name = name, type = type, age = age, owner = owner)
            viewModel.addPet(newPet)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PetAdapter(
            onDeleteClick = { pet -> viewModel.deletePet(pet) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.pets.collect { pets ->
                adapter.submitList(pets)
            }
        }

        viewModel.loadPets()

        binding.btnAddPet.setOnClickListener {
            val intent = Intent(this, AddPetActivity::class.java)
            addPetLauncher.launch(intent)
        }
    }
}