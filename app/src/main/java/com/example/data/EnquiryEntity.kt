package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "enquiries")
data class EnquiryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val customerName: String,
    val phoneNumber: String,
    val localityArea: String,
    val propertyType: String,
    val pestConcern: String,
    val preferredDate: String,
    val additionalNotes: String,
    val estimatedPriceRange: String,
    val timestamp: Long = System.currentTimeMillis(),
    val status: String = "Pending WhatsApp Confirmation"
)
