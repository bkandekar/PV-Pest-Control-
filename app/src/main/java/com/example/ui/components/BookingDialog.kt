package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PestControl
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalPrimary
import com.example.ui.theme.LimeGreenAccent
import com.example.viewmodel.PestViewModel

@Composable
fun BookingDialog(
    viewModel: PestViewModel,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val customerName by viewModel.customerName.collectAsStateWithLifecycle()
    val phoneNumber by viewModel.phoneNumber.collectAsStateWithLifecycle()
    val localityArea by viewModel.localityArea.collectAsStateWithLifecycle()
    val preferredDate by viewModel.preferredDate.collectAsStateWithLifecycle()
    val additionalNotes by viewModel.additionalNotes.collectAsStateWithLifecycle()
    val pestConcern by viewModel.bookingPestConcern.collectAsStateWithLifecycle()
    val propertyType by viewModel.bookingPropertyType.collectAsStateWithLifecycle()

    val nameError by viewModel.nameError.collectAsStateWithLifecycle()
    val phoneError by viewModel.phoneError.collectAsStateWithLifecycle()

    val minEst by viewModel.minEstimate.collectAsStateWithLifecycle()
    val maxEst by viewModel.maxEstimate.collectAsStateWithLifecycle()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 10.dp,
            shadowElevation = 12.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header with Close Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Book Inspection",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = CharcoalPrimary
                        )
                        Text(
                            text = "100% Free On-Site Quote • Fast WhatsApp Confirm",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("booking_close_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Dialog",
                            tint = CharcoalPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Price Range Badge Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = CharcoalDark),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "ESTIMATED TREATMENT COST",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = LimeGreenAccent,
                                letterSpacing = 0.8.sp
                            )
                            Text(
                                text = "₹$minEst – ₹$maxEst",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                        Surface(
                            color = LimeGreenAccent.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Free Visit",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = LimeGreenAccent
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Form Fields
                // Full Name
                OutlinedTextField(
                    value = customerName,
                    onValueChange = { viewModel.updateCustomerName(it) },
                    label = { Text("Full Name *") },
                    placeholder = { Text("e.g. Ramesh Kulkarni") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = CharcoalPrimary) },
                    isError = nameError != null,
                    supportingText = nameError?.let { { Text(it, color = MaterialTheme.colorScheme.error) } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("booking_name_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = CharcoalPrimary)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Phone Number
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { viewModel.updatePhoneNumber(it) },
                    label = { Text("Phone Number *") },
                    placeholder = { Text("10-digit mobile number") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = CharcoalPrimary) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    isError = phoneError != null,
                    supportingText = phoneError?.let { { Text(it, color = MaterialTheme.colorScheme.error) } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("booking_phone_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = CharcoalPrimary)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Locality / Area
                OutlinedTextField(
                    value = localityArea,
                    onValueChange = { viewModel.updateLocalityArea(it) },
                    label = { Text("Locality / Area in Amravati") },
                    placeholder = { Text("e.g. Rajendra Colony, Shyam Nagar") },
                    leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = CharcoalPrimary) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("booking_locality_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = CharcoalPrimary)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Pest Concern (Pre-filled / Editable)
                OutlinedTextField(
                    value = pestConcern,
                    onValueChange = { viewModel.updateBookingPestConcern(it) },
                    label = { Text("Pest Concern") },
                    leadingIcon = { Icon(Icons.Default.PestControl, contentDescription = null, tint = CharcoalPrimary) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("booking_pest_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = CharcoalPrimary)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Property Type (Pre-filled / Editable)
                OutlinedTextField(
                    value = propertyType,
                    onValueChange = { viewModel.updateBookingPropertyType(it) },
                    label = { Text("Property Type") },
                    leadingIcon = { Icon(Icons.Default.Home, contentDescription = null, tint = CharcoalPrimary) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("booking_property_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = CharcoalPrimary)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Preferred Date
                OutlinedTextField(
                    value = preferredDate,
                    onValueChange = { viewModel.updatePreferredDate(it) },
                    label = { Text("Preferred Inspection Date") },
                    leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = CharcoalPrimary) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("booking_date_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = CharcoalPrimary)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Additional Notes
                OutlinedTextField(
                    value = additionalNotes,
                    onValueChange = { viewModel.updateAdditionalNotes(it) },
                    label = { Text("Additional Notes (Optional)") },
                    placeholder = { Text("Any specific timing or details...") },
                    leadingIcon = { Icon(Icons.Default.Note, contentDescription = null, tint = CharcoalPrimary) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("booking_notes_input"),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = CharcoalPrimary)
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Submit via WhatsApp Button
                Button(
                    onClick = { viewModel.submitBookingAndOpenWhatsApp(context) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("booking_submit_whatsapp_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CharcoalPrimary,
                        contentColor = LimeGreenAccent
                    ),
                    shape = RoundedCornerShape(14.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = LimeGreenAccent,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Confirm Booking on WhatsApp",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "*You will be redirected to WhatsApp to send your booking message directly to PV Pest Control (9067257872).",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
