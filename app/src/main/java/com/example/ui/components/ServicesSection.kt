package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CorporateFare
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PestDataDefaults
import com.example.model.ServicePackage
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalPrimary
import com.example.ui.theme.LimeGreenAccent
import com.example.ui.theme.LimeGreenContainer
import com.example.ui.theme.OnLimeGreenContainer
import com.example.viewmodel.PestViewModel

import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Shield

@Composable
fun ServicesSection(
    viewModel: PestViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        // Section Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text(
                text = "OUR SERVICES",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = LimeGreenAccent,
                letterSpacing = 1.sp
            )
            Text(
                text = "Targeted Treatment Packages",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = CharcoalPrimary
            )
            Text(
                text = "Swipe to explore specialized treatments for homes & businesses in Amravati",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Horizontal Stack Carousel
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp)
        ) {
            items(PestDataDefaults.servicePackages) { service ->
                ServiceCardItem(
                    service = service,
                    onBookClick = {
                        viewModel.openBookingDialog(presetPest = service.title)
                    }
                )
            }
        }
    }
}

@Composable
fun ServiceCardItem(
    service: ServicePackage,
    onBookClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .shadow(4.dp, RoundedCornerShape(18.dp))
            .testTag("service_card_${service.title.lowercase().replace(" ", "_")}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E6EA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                // Top Tag & Warranty Badge
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = LimeGreenContainer,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = service.tag,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnLimeGreenContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    Text(
                        text = service.warrantyText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = CharcoalPrimary
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Service Image Placeholder Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(95.dp)
                        .background(CharcoalDark, RoundedCornerShape(12.dp))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhotoCamera,
                            contentDescription = "Image Placeholder for ${service.title}",
                            tint = LimeGreenAccent,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "[ Image: ${service.title} Treatment ]",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Title & Subtitle
                Text(
                    text = service.title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = CharcoalPrimary
                )

                Text(
                    text = service.subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    lineHeight = 16.sp,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Price Badge
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Starting at ",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = service.startingPrice,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = CharcoalPrimary
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Key Features List
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    service.keyFeatures.forEach { feature ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(CharcoalDark, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = LimeGreenAccent,
                                    modifier = Modifier.size(10.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = feature,
                                fontSize = 11.sp,
                                color = CharcoalPrimary,
                                maxLines = 1
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Book Button
            Button(
                onClick = onBookClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CharcoalPrimary,
                    contentColor = LimeGreenAccent
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Book ${service.title.take(16)}...",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}
