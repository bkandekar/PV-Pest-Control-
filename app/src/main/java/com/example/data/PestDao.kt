package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PestDao {
    @Query("SELECT * FROM enquiries ORDER BY timestamp DESC")
    fun getAllEnquiries(): Flow<List<EnquiryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnquiry(enquiry: EnquiryEntity): Long

    @Query("DELETE FROM enquiries WHERE id = :id")
    suspend fun deleteEnquiry(id: Long)

    @Query("SELECT * FROM quotes ORDER BY timestamp DESC")
    fun getAllQuotes(): Flow<List<QuoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteEntity): Long

    @Query("DELETE FROM quotes WHERE id = :id")
    suspend fun deleteQuote(id: Long)
}
