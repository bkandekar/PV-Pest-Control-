package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EnquiryEntity::class, QuoteEntity::class], version = 1, exportSchema = false)
abstract class PestDatabase : RoomDatabase() {
    abstract fun pestDao(): PestDao

    companion object {
        @Volatile
        private var INSTANCE: PestDatabase? = null

        fun getDatabase(context: Context): PestDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PestDatabase::class.java,
                    "pv_pest_control.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
