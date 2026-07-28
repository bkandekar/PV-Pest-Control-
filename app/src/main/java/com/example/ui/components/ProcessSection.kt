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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalPrimary
import com.example.ui.theme.LimeGreenAccent
import com.example.ui.theme.LimeGreenContainer
import com.example.ui.theme.OnLimeGreenContainer

@Composable
fun ProcessSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Text(
            text = "HOW IT WORKS",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = CharcoalPrimary.copy(alpha = 0.7f),
            letterSpacing = 0.8.sp
        )
        Text(
            text = "4-Step Hassle-Free Eradication",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = CharcoalPrimary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E6EA))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                ProcessStepItem(
                    stepNum = "1",
                    title = "Book & Calculate",
                    desc = "Use our instant cost calculator or book a free on-site consultation via WhatsApp.",
                    icon = Icons.Default.CalendarMonth
                )

                ProcessStepItem(
                    stepNum = "2",
                    title = "On-Site Inspection",
                    desc = "Certified Amravati technician inspects wall crevices, furniture, and pest nesting grounds.",
                    icon = Icons.Default.Search
                )

                ProcessStepItem(
                    stepNum = "3",
                    title = "Eco Treatment",
                    desc = "Targeted herbal gel baiting & odorless chemical spray. No need to clear kitchen!",
                    icon = Icons.Default.Build
                )

                ProcessStepItem(
                    stepNum = "4",
                    title = "Follow-Up Guarantee",
                    desc = "Enjoy 100% pest-free guarantee with free on-call service if pests recur in warranty.",
                    icon = Icons.Default.VerifiedUser
                )
            }
        }
    }
}

@Composable
private fun ProcessStepItem(
    stepNum: String,
    title: String,
    desc: String,
    icon: ImageVector
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(CharcoalPrimary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stepNum,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                color = LimeGreenAccent
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = CharcoalPrimary
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = desc,
                fontSize = 12.sp,
                color = Color.Gray,
                lineHeight = 16.sp
            )
        }
    }
}
