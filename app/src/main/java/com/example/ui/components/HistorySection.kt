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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalPrimary
import com.example.ui.theme.LimeGreenAccent
import com.example.ui.theme.LimeGreenContainer
import com.example.ui.theme.OnLimeGreenContainer
import com.example.viewmodel.PestViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistorySection(
    viewModel: PestViewModel,
    modifier: Modifier = Modifier
) {
    val savedQuotes by viewModel.savedQuotes.collectAsStateWithLifecycle()
    val savedEnquiries by viewModel.savedEnquiries.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(CharcoalPrimary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = null,
                    tint = LimeGreenAccent,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Saved Quotes & Enquiries",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CharcoalPrimary
                )
                Text(
                    text = "Track your generated estimates and sent WhatsApp enquiries",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        // Section 1: Saved Quotes
        Text(
            text = "SAVED CALCULATOR QUOTES",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = CharcoalPrimary.copy(alpha = 0.7f),
            letterSpacing = 0.8.sp,
            modifier = Modifier.padding(vertical = 6.dp)
        )

        if (savedQuotes.isEmpty()) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                color = Color(0xFFF8F9FA),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E6EA))
            ) {
                Text(
                    text = "No saved quotes yet. Use the calculator to save estimates!",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        } else {
            savedQuotes.forEach { quote ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E6EA))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${quote.pestConcern} • ${quote.propertyType}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = CharcoalPrimary
                            )
                            Text(
                                text = "Size: ${quote.propertySize} | ${quote.frequency}",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Estimate: ₹${quote.minPrice} – ₹${quote.maxPrice}",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 14.sp,
                                color = CharcoalDark
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Button(
                                onClick = {
                                    viewModel.openBookingDialog(presetPest = quote.pestConcern, presetProperty = quote.propertyType)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = CharcoalPrimary, contentColor = LimeGreenAccent),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.testTag("history_book_quote_${quote.id}")
                            ) {
                                Text(text = "Book", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }

                            IconButton(
                                onClick = { viewModel.deleteQuote(quote.id) },
                                modifier = Modifier.testTag("history_delete_quote_${quote.id}")
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Gray)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Section 2: Sent Booking Enquiries
        Text(
            text = "SENT BOOKING ENQUIRIES",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = CharcoalPrimary.copy(alpha = 0.7f),
            letterSpacing = 0.8.sp,
            modifier = Modifier.padding(vertical = 6.dp)
        )

        if (savedEnquiries.isEmpty()) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                color = Color(0xFFF8F9FA),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E6EA))
            ) {
                Text(
                    text = "No booking enquiries sent yet.",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        } else {
            val sdf = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
            savedEnquiries.forEach { enquiry ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = CharcoalDark)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = enquiry.customerName,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color.White
                            )

                            Surface(
                                color = LimeGreenContainer,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = enquiry.status,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = OnLimeGreenContainer,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Phone: ${enquiry.phoneNumber} | Locality: ${enquiry.localityArea}",
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                        Text(
                            text = "Pest: ${enquiry.pestConcern} | Quote: ${enquiry.estimatedPriceRange}",
                            fontSize = 11.sp,
                            color = LimeGreenAccent,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Sent: ${sdf.format(Date(enquiry.timestamp))}",
                                fontSize = 10.sp,
                                color = Color.Gray
                            )

                            IconButton(
                                onClick = { viewModel.deleteEnquiry(enquiry.id) },
                                modifier = Modifier
                                    .size(28.dp)
                                    .testTag("history_delete_enquiry_${enquiry.id}")
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.LightGray, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
