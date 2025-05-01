package com.example.modul6pamrecyclerview.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PetEntity::class], version = 1, exportSchema = false)
abstract class PetDB : RoomDatabase() {

    abstract fun petDao(): PetDao

    companion object {
        @Volatile
        private var INSTANCE: PetDB? = null

        fun getInstance(context: Context): PetDB {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    PetDB::class.java,
                    "pet_db"
                ).build()
                INSTANCE = instance
            }
            return instance
        }
    }
}
