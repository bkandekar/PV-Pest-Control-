package com.example.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.EnquiryEntity
import com.example.data.PestRepository
import com.example.data.QuoteEntity
import com.example.model.PestConcernOption
import com.example.model.PestDataDefaults
import com.example.model.PropertyOption
import com.example.model.PropertySizeOption
import com.example.model.TreatmentFrequencyOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PestViewModel(private val repository: PestRepository) : ViewModel() {

    // Calculator State
    private val _selectedPropertyType = MutableStateFlow(PestDataDefaults.propertyTypes[0])
    val selectedPropertyType: StateFlow<PropertyOption> = _selectedPropertyType.asStateFlow()

    private val _selectedPropertySize = MutableStateFlow(PestDataDefaults.propertySizes[1])
    val selectedPropertySize: StateFlow<PropertySizeOption> = _selectedPropertySize.asStateFlow()

    private val _selectedPestConcern = MutableStateFlow(PestDataDefaults.pestConcerns[0])
    val selectedPestConcern: StateFlow<PestConcernOption> = _selectedPestConcern.asStateFlow()

    private val _selectedFrequency = MutableStateFlow(PestDataDefaults.treatmentFrequencies[0])
    val selectedFrequency: StateFlow<TreatmentFrequencyOption> = _selectedFrequency.asStateFlow()

    private val _minEstimate = MutableStateFlow(850)
    val minEstimate: StateFlow<Int> = _minEstimate.asStateFlow()

    private val _maxEstimate = MutableStateFlow(1100)
    val maxEstimate: StateFlow<Int> = _maxEstimate.asStateFlow()

    // Booking Dialog State
    private val _isBookingDialogOpen = MutableStateFlow(false)
    val isBookingDialogOpen: StateFlow<Boolean> = _isBookingDialogOpen.asStateFlow()

    private val _customerName = MutableStateFlow("")
    val customerName: StateFlow<String> = _customerName.asStateFlow()

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()

    private val _localityArea = MutableStateFlow("")
    val localityArea: StateFlow<String> = _localityArea.asStateFlow()

    private val _preferredDate = MutableStateFlow("")
    val preferredDate: StateFlow<String> = _preferredDate.asStateFlow()

    private val _additionalNotes = MutableStateFlow("")
    val additionalNotes: StateFlow<String> = _additionalNotes.asStateFlow()

    private val _bookingPestConcern = MutableStateFlow("General Pest Control")
    val bookingPestConcern: StateFlow<String> = _bookingPestConcern.asStateFlow()

    private val _bookingPropertyType = MutableStateFlow("Apartment")
    val bookingPropertyType: StateFlow<String> = _bookingPropertyType.asStateFlow()

    private val _nameError = MutableStateFlow<String?>(null)
    val nameError: StateFlow<String?> = _nameError.asStateFlow()

    private val _phoneError = MutableStateFlow<String?>(null)
    val phoneError: StateFlow<String?> = _phoneError.asStateFlow()

    private val _bookingSuccessMessage = MutableStateFlow<String?>(null)
    val bookingSuccessMessage: StateFlow<String?> = _bookingSuccessMessage.asStateFlow()

    // Room Database State
    val savedEnquiries: StateFlow<List<EnquiryEntity>> = repository.allEnquiries
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val savedQuotes: StateFlow<List<QuoteEntity>> = repository.allQuotes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        recalculateEstimate()
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        _preferredDate.value = sdf.format(Date())
    }

    fun setPropertyType(option: PropertyOption) {
        _selectedPropertyType.value = option
        _bookingPropertyType.value = option.name
        recalculateEstimate()
    }

    fun setPropertySize(option: PropertySizeOption) {
        _selectedPropertySize.value = option
        recalculateEstimate()
    }

    fun setPestConcern(option: PestConcernOption) {
        _selectedPestConcern.value = option
        _bookingPestConcern.value = option.name
        recalculateEstimate()
    }

    fun setFrequency(option: TreatmentFrequencyOption) {
        _selectedFrequency.value = option
        recalculateEstimate()
    }

    private fun recalculateEstimate() {
        val basePrice = _selectedPestConcern.value.basePrice
        val propMult = _selectedPropertyType.value.multiplier
        val sizeMult = _selectedPropertySize.value.multiplier
        val discount = _selectedFrequency.value.discountPercent

        val rawCalculated = basePrice * propMult * sizeMult
        val discounted = rawCalculated * (1.0 - discount / 100.0)

        var min = (discounted * 0.95).toInt()
        var max = (discounted * 1.15).toInt()

        // Round to clean multiples of 50
        min = (min / 50) * 50
        max = (max / 50) * 50

        if (min < 700) min = 700
        if (max < min + 200) max = min + 250

        _minEstimate.value = min
        _maxEstimate.value = max
    }

    fun openBookingDialog(presetPest: String? = null, presetProperty: String? = null) {
        presetPest?.let { _bookingPestConcern.value = it }
        presetProperty?.let { _bookingPropertyType.value = it }
        _nameError.value = null
        _phoneError.value = null
        _isBookingDialogOpen.value = true
    }

    fun closeBookingDialog() {
        _isBookingDialogOpen.value = false
    }

    fun updateCustomerName(valStr: String) {
        _customerName.value = valStr
        if (valStr.isNotBlank()) _nameError.value = null
    }

    fun updatePhoneNumber(valStr: String) {
        _phoneNumber.value = valStr
        if (valStr.length >= 10) _phoneError.value = null
    }

    fun updateLocalityArea(valStr: String) {
        _localityArea.value = valStr
    }

    fun updatePreferredDate(valStr: String) {
        _preferredDate.value = valStr
    }

    fun updateAdditionalNotes(valStr: String) {
        _additionalNotes.value = valStr
    }

    fun updateBookingPestConcern(valStr: String) {
        _bookingPestConcern.value = valStr
    }

    fun updateBookingPropertyType(valStr: String) {
        _bookingPropertyType.value = valStr
    }

    fun saveCurrentQuoteToHistory(context: Context) {
        viewModelScope.launch {
            val quote = QuoteEntity(
                propertyType = _selectedPropertyType.value.name,
                propertySize = _selectedPropertySize.value.name,
                pestConcern = _selectedPestConcern.value.name,
                frequency = _selectedFrequency.value.name,
                minPrice = _minEstimate.value,
                maxPrice = _maxEstimate.value
            )
            repository.saveQuote(quote)
            Toast.makeText(context, "Quote saved to history!", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteQuote(id: Long) {
        viewModelScope.launch {
            repository.deleteQuote(id)
        }
    }

    fun deleteEnquiry(id: Long) {
        viewModelScope.launch {
            repository.deleteEnquiry(id)
        }
    }

    fun submitBookingAndOpenWhatsApp(context: Context) {
        val name = _customerName.value.trim()
        val phone = _phoneNumber.value.trim()

        var isValid = true
        if (name.isBlank()) {
            _nameError.value = "Please enter your full name"
            isValid = false
        }

        if (phone.isBlank() || phone.length < 10) {
            _phoneError.value = "Please enter a valid 10-digit phone number"
            isValid = false
        }

        if (!isValid) return

        val locality = _localityArea.value.ifBlank { "Amravati" }
        val propType = _bookingPropertyType.value
        val pest = _bookingPestConcern.value
        val date = _preferredDate.value.ifBlank { "As soon as possible" }
        val notes = _additionalNotes.value
        val priceRange = "₹${_minEstimate.value} - ₹${_maxEstimate.value}"

        // Save enquiry to local database
        viewModelScope.launch {
            val entity = EnquiryEntity(
                customerName = name,
                phoneNumber = phone,
                localityArea = locality,
                propertyType = propType,
                pestConcern = pest,
                preferredDate = date,
                additionalNotes = notes,
                estimatedPriceRange = priceRange
            )
            repository.saveEnquiry(entity)
        }

        // Construct WhatsApp Message
        val messageStr = """
            *NEW BOOKING ENQUIRY - PV PEST CONTROL*
            ----------------------------------------
            👤 *Customer Name:* $name
            📞 *Phone Number:* $phone
            📍 *Locality/Area:* $locality
            🏡 *Property Type:* $propType (${_selectedPropertySize.value.name})
            🦟 *Pest Concern:* $pest
            📅 *Preferred Date:* $date
            💰 *Estimated Quote:* $priceRange
            📝 *Notes:* ${if (notes.isBlank()) "None" else notes}
            ----------------------------------------
            *PV Pest Control Services*
            Shop No 04, Rana Complex, Shyam Nagar, Amravati
            Phone: 9067257872
        """.trimIndent()

        _isBookingDialogOpen.value = false
        _bookingSuccessMessage.value = "Opening WhatsApp to confirm booking..."

        try {
            val encodedMsg = URLEncoder.encode(messageStr, "UTF-8")
            val whatsappUrl = "https://wa.me/919067257872?text=$encodedMsg"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappUrl)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Fallback to direct Phone Call or SMS if WhatsApp fails
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:9067257872")).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(callIntent)
        }
    }

    fun makeDirectCall(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:9067257872")).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Call 9067257872", Toast.LENGTH_LONG).show()
        }
    }

    fun openGoogleMapsLocation(context: Context) {
        try {
            val address = "Shop No 04, Rana Complex, Congress Nagar Rd, Rajendra Colony, Shyam Nagar, Amravati, Maharashtra 444606"
            val uri = Uri.parse("geo:0,0?q=" + Uri.encode(address))
            val mapIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(mapIntent)
        } catch (e: Exception) {
            val webUri = Uri.parse("https://maps.google.com/?q=PV+Pest+Control+Services+Amravati")
            val webIntent = Intent(Intent.ACTION_VIEW, webUri).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(webIntent)
        }
    }

    fun clearSuccessMessage() {
        _bookingSuccessMessage.value = null
    }
}

class PestViewModelFactory(private val repository: PestRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PestViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PestViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
