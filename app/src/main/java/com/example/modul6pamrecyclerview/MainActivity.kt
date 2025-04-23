package com.example.modul6pamrecyclerview

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var petAdapter: PetAdapter
    private val petList = arrayListOf(
        PetModel("Tommy", "Anjing", 3, "Rudi"),
        PetModel("Milo", "Kucing", 2, "Dina"),
        PetModel("Lola", "Kelinci", 1, "Rina"),
        PetModel("Bubu", "Hamster", 1, "Andi")
    )
    private val addPetLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val name = data?.getStringExtra("name") ?: ""
            val type = data?.getStringExtra("type") ?: ""
            val age = data?.getIntExtra("age", 0) ?: 0
            val owner = data?.getStringExtra("owner") ?: ""

            val newPet = PetModel(name, type, age, owner)
            petList.add(newPet)
            petAdapter.notifyItemInserted(petList.size - 1)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val btnAdd = findViewById<FloatingActionButton>(R.id.btnAddPet)

        petAdapter = PetAdapter(petList) { position ->
            petList.removeAt(position)
            petAdapter.notifyItemRemoved(position)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = petAdapter

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddPetActivity::class.java)
            addPetLauncher.launch(intent)
        }
    }
}