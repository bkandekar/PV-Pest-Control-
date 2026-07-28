package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalPrimary
import com.example.ui.theme.LimeGreenAccent

@Composable
fun PainPointsSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Text(
            text = "THE PV PEST CONTROL DIFFERENCE",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = CharcoalPrimary.copy(alpha = 0.7f),
            letterSpacing = 0.8.sp
        )
        Text(
            text = "Common Frustrations vs. PV Solutions",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = CharcoalPrimary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Typical Local Spray (Bad)
            Card(
                modifier = Modifier
                    .weight(1f)
                    .shadow(2.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0F0))
            ) {
                Column(
                    modifier = Modifier.padding(14.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Cancel,
                            contentDescription = null,
                            tint = Color(0xFFD32F2F),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Unreliable Services",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Color(0xFFB71C1C)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    PainPointItem("\"Pests return in 2 weeks after one spray\"")
                    PainPointItem("\"Harsh chemical smell & toxic fumes\"")
                    PainPointItem("\"Hidden extra costs at service time\"")
                    PainPointItem("\"Unverified technicians in my home\"")
                    PainPointItem("\"Technicians late or don't show up\"")
                }
            }

            // PV Pest Control Solution (Good)
            Card(
                modifier = Modifier
                    .weight(1f)
                    .shadow(4.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CharcoalDark)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = LimeGreenAccent,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "PV Pest Control",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = LimeGreenAccent
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    SolutionPointItem("AMC long-term plans & written guarantee")
                    SolutionPointItem("Govt-approved, odorless, kid & pet safe")
                    SolutionPointItem("Transparent instant cost calculator")
                    SolutionPointItem("Verified, trained, uniformed team with ID")
                    SolutionPointItem("On-time arrival + appointment alerts")
                }
            }
        }
    }
}

@Composable
private fun PainPointItem(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 3.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(text = "• ", fontSize = 11.sp, color = Color(0xFFB71C1C), fontWeight = FontWeight.Bold)
        Text(text = text, fontSize = 11.sp, color = Color(0xFF424242), lineHeight = 14.sp)
    }
}

@Composable
private fun SolutionPointItem(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 3.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(text = "✓ ", fontSize = 11.sp, color = LimeGreenAccent, fontWeight = FontWeight.Bold)
        Text(text = text, fontSize = 11.sp, color = Color.White, lineHeight = 14.sp)
    }
}

