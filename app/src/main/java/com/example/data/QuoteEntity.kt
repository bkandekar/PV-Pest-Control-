package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val propertyType: String,
    val propertySize: String,
    val pestConcern: String,
    val frequency: String,
    val minPrice: Int,
    val maxPrice: Int,
    val timestamp: Long = System.currentTimeMillis()
)
