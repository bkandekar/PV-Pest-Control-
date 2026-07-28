package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.model.PestDataDefaults
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalMedium
import com.example.ui.theme.CharcoalPrimary
import com.example.ui.theme.LimeGreenAccent
import com.example.ui.theme.LimeGreenContainer
import com.example.ui.theme.OnLimeGreenContainer
import com.example.viewmodel.PestViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CalculatorCard(
    viewModel: PestViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val selectedPropertyType by viewModel.selectedPropertyType.collectAsStateWithLifecycle()
    val selectedPropertySize by viewModel.selectedPropertySize.collectAsStateWithLifecycle()
    val selectedPestConcern by viewModel.selectedPestConcern.collectAsStateWithLifecycle()
    val selectedFrequency by viewModel.selectedFrequency.collectAsStateWithLifecycle()

    val minEst by viewModel.minEstimate.collectAsStateWithLifecycle()
    val maxEst by viewModel.maxEstimate.collectAsStateWithLifecycle()

    val animatedMinEst by animateIntAsState(targetValue = minEst, animationSpec = tween(400), label = "minEstAnim")
    val animatedMaxEst by animateIntAsState(targetValue = maxEst, animationSpec = tween(400), label = "maxEstAnim")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Section Title
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
                    imageVector = Icons.Default.Calculate,
                    contentDescription = null,
                    tint = LimeGreenAccent,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Instant Pest Treatment Calculator",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CharcoalPrimary
                )
                Text(
                    text = "Get real-time cost estimate in seconds",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(6.dp, RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Step 1: Property Type Selection
                Text(
                    text = "1. SELECT PROPERTY TYPE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = CharcoalPrimary,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PestDataDefaults.propertyTypes.forEach { option ->
                        val isSelected = option.name == selectedPropertyType.name
                        Surface(
                            modifier = Modifier
                                .clickable { viewModel.setPropertyType(option) }
                                .testTag("calc_prop_type_${option.name.lowercase().replace(" ", "_")}"),
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) CharcoalPrimary else Color(0xFFF1F3F5),
                            border = if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E6EA))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (isSelected) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = LimeGreenAccent, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                }
                                Text(
                                    text = option.name,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else CharcoalPrimary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Step 2: Property Size
                Text(
                    text = "2. PROPERTY CARPET AREA",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = CharcoalPrimary,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PestDataDefaults.propertySizes.forEach { sizeOpt ->
                        val isSelected = sizeOpt.name == selectedPropertySize.name
                        Surface(
                            modifier = Modifier
                                .clickable { viewModel.setPropertySize(sizeOpt) }
                                .testTag("calc_size_${sizeOpt.name.lowercase().replace(" ", "_")}"),
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) CharcoalPrimary else Color(0xFFF1F3F5),
                            border = if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E6EA))
                        ) {
                            Text(
                                text = sizeOpt.name,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) LimeGreenAccent else CharcoalPrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Step 3: Pest Concern
                Text(
                    text = "3. PEST CONCERN",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = CharcoalPrimary,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PestDataDefaults.pestConcerns.forEach { concernOpt ->
                        val isSelected = concernOpt.name == selectedPestConcern.name
                        Surface(
                            modifier = Modifier
                                .clickable { viewModel.setPestConcern(concernOpt) }
                                .testTag("calc_pest_${concernOpt.name.lowercase().replace(" ", "_")}"),
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) LimeGreenContainer else Color(0xFFF1F3F5),
                            border = if (isSelected) androidx.compose.foundation.BorderStroke(1.5.dp, OnLimeGreenContainer) else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E6EA))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = OnLimeGreenContainer,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                }
                                Text(
                                    text = concernOpt.name,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                                    color = if (isSelected) OnLimeGreenContainer else CharcoalPrimary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Step 4: Frequency / AMC Selection
                Text(
                    text = "4. TREATMENT FREQUENCY",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = CharcoalPrimary,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(6.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PestDataDefaults.treatmentFrequencies.forEach { freqOpt ->
                        val isSelected = freqOpt.name == selectedFrequency.name
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.setFrequency(freqOpt) }
                                .testTag("calc_freq_${freqOpt.name.lowercase().replace(" ", "_")}"),
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) CharcoalDark else Color(0xFFF8F9FA),
                            border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, LimeGreenAccent) else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E6EA))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .background(
                                                if (isSelected) LimeGreenAccent else Color.LightGray.copy(alpha = 0.5f),
                                                CircleShape
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (isSelected) {
                                            Box(
                                                modifier = Modifier
                                                    .size(8.dp)
                                                    .background(CharcoalDark, CircleShape)
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = freqOpt.name,
                                        fontSize = 13.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.White else CharcoalPrimary
                                    )
                                }

                                freqOpt.badgeText?.let { badge ->
                                    Surface(
                                        color = LimeGreenAccent,
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(Icons.Default.LocalOffer, contentDescription = null, tint = CharcoalDark, modifier = Modifier.size(10.dp))
                                            Spacer(modifier = Modifier.width(2.dp))
                                            Text(
                                                text = badge,
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.ExtraBold,
                                                color = CharcoalDark
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // LIVE ESTIMATE DISPLAY BOX
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CharcoalDark)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "ESTIMATED TREATMENT COST",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = LimeGreenAccent,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "₹$animatedMinEst – ₹$animatedMaxEst",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.7f),
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "*Final price confirmed after free on-site inspection.",
                                fontSize = 11.sp,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Book Package Button
                        Button(
                            onClick = { viewModel.openBookingDialog(selectedPestConcern.name, selectedPropertyType.name) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("calc_book_package_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = LimeGreenAccent,
                                contentColor = CharcoalDark
                            ),
                            shape = RoundedCornerShape(12.dp),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                        ) {
                            Text(
                                text = "Book This Package",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Save Quote Button
                        OutlinedButton(
                            onClick = { viewModel.saveCurrentQuoteToHistory(context) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(42.dp)
                                .testTag("calc_save_quote_button"),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.4f)),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Bookmark, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = "Save Quote to History", fontSize = 12.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
        }
    }
}
