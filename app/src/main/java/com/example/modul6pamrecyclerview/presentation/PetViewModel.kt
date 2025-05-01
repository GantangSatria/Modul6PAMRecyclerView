package com.example.modul6pamrecyclerview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul6pamrecyclerview.database.PetEntity
import com.example.modul6pamrecyclerview.repository.PetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PetViewModel(private val repository: PetRepository) : ViewModel() {

    private val _pets = MutableStateFlow<List<PetEntity>>(emptyList())
    val pets: StateFlow<List<PetEntity>> get() = _pets

    fun loadPets() {
        viewModelScope.launch {
            _pets.value = repository.getAllPets()
        }
    }

    fun addPet(pet: PetEntity) {
        viewModelScope.launch {
            repository.insertPet(pet)
            loadPets()
        }
    }

    fun deletePet(pet: PetEntity) {
        viewModelScope.launch {
            repository.deletePet(pet)
            loadPets()
        }
    }

}
