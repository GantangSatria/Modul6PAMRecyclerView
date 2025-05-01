package com.example.modul6pamrecyclerview.repository

import com.example.modul6pamrecyclerview.database.PetDao
import com.example.modul6pamrecyclerview.database.PetEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PetRepository(private val petDao: PetDao) {

    suspend fun getAllPets(): List<PetEntity> {
        return withContext(Dispatchers.IO) {
            petDao.getAllPets()
        }
    }

    suspend fun insertPet(pet: PetEntity) {
        withContext(Dispatchers.IO) {
            petDao.insertPet(pet)
        }
    }

    suspend fun deletePet(pet: PetEntity) {
        withContext(Dispatchers.IO) {
            petDao.deletePet(pet)
        }
    }
}
