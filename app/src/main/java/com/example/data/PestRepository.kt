package com.example.data

import kotlinx.coroutines.flow.Flow

class PestRepository(private val pestDao: PestDao) {
    val allEnquiries: Flow<List<EnquiryEntity>> = pestDao.getAllEnquiries()
    val allQuotes: Flow<List<QuoteEntity>> = pestDao.getAllQuotes()

    suspend fun saveEnquiry(enquiry: EnquiryEntity): Long {
        return pestDao.insertEnquiry(enquiry)
    }

    suspend fun deleteEnquiry(id: Long) {
        pestDao.deleteEnquiry(id)
    }

    suspend fun saveQuote(quote: QuoteEntity): Long {
        return pestDao.insertQuote(quote)
    }

    suspend fun deleteQuote(id: Long) {
        pestDao.deleteQuote(id)
    }
}
