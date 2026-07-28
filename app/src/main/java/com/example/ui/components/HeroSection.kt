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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalMedium
import com.example.ui.theme.CharcoalPrimary
import com.example.ui.theme.GoldStar
import com.example.ui.theme.LimeGreenAccent
import com.example.ui.theme.LimeGreenContainer
import com.example.ui.theme.OnLimeGreenContainer
import com.example.viewmodel.PestViewModel

@Composable
fun HeroSection(
    viewModel: PestViewModel,
    onNavigateToCalculator: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Top Location & Rating Banner
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Surface(
                color = LimeGreenContainer,
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Eco,
                        contentDescription = null,
                        tint = OnLimeGreenContainer,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Amravati's #1 Eco Pest Agency",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnLimeGreenContainer
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = GoldStar,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "4.9/5",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = CharcoalPrimary
                )
                Text(
                    text = " (1,200+ Reviews)",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Hero Main Banner Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = CharcoalDark)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(CharcoalDark, CharcoalMedium)
                        )
                    )
                    .padding(18.dp)
            ) {
                Column {
                    // Headline
                    Text(
                        text = "Tired of Cockroaches, Termites & Bed Bugs Coming Back Again and Again?",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        lineHeight = 28.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Get guaranteed, long-lasting, eco-safe eradication — not just a temporary spray. PV Pest Control brings 15+ years of trusted protection across Amravati.",
                        fontSize = 13.sp,
                        color = Color.White.copy(alpha = 0.88f),
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Primary CTAs
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = { viewModel.openBookingDialog() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = LimeGreenAccent,
                                contentColor = CharcoalDark
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .testTag("hero_book_inspection_button")
                        ) {
                            Text(
                                text = "Book Free Inspection",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }

                        OutlinedButton(
                            onClick = onNavigateToCalculator,
                            border = androidx.compose.foundation.BorderStroke(1.5.dp, LimeGreenAccent),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = LimeGreenAccent),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .testTag("hero_calculate_cost_button")
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Calculate,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Calculate Cost",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Floating Overlapping Trust Badge Cards
        Text(
            text = "WHY AMRAVATI TRUSTS PV PEST CONTROL",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = CharcoalPrimary.copy(alpha = 0.7f),
            letterSpacing = 0.8.sp,
            modifier = Modifier.padding(start = 2.dp, bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TrustBadgeCard(
                icon = Icons.Default.Shield,
                title = "10,000+ Homes",
                subtitle = "Protected in Amravati",
                modifier = Modifier.weight(1f)
            )
            TrustBadgeCard(
                icon = Icons.Default.CheckCircle,
                title = "Govt. Certified",
                subtitle = "Authorized Pesticides",
                modifier = Modifier.weight(1f)
            )
            TrustBadgeCard(
                icon = Icons.Default.ChildCare,
                title = "100% Safe",
                subtitle = "For Kids & Pets",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun TrustBadgeCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .border(1.dp, Color(0xFFE2E6EA), RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(LimeGreenContainer, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = OnLimeGreenContainer,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = CharcoalPrimary
            )
            Text(
                text = subtitle,
                fontSize = 10.sp,
                color = Color.Gray,
                lineHeight = 12.sp
            )
        }
    }
}
