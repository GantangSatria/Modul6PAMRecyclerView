package com.example.modul6pamrecyclerview.database

import androidx.room.*

@Dao
interface PetDao {

    @Query("SELECT * FROM pets")
    suspend fun getAllPets(): List<PetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: PetEntity)

    @Delete
    suspend fun deletePet(pet: PetEntity)
}
