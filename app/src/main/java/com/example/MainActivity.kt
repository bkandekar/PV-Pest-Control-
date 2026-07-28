package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.RateReview
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.PestDatabase
import com.example.data.PestRepository
import com.example.ui.components.AMCSavingsCard
import com.example.ui.components.BeforeAfterShowcase
import com.example.ui.components.BookingDialog
import com.example.ui.components.CalculatorCard
import com.example.ui.components.ContactAndFooter
import com.example.ui.components.EmergencyBanner
import com.example.ui.components.HeaderAppBar
import com.example.ui.components.HeroSection
import com.example.ui.components.HistorySection
import com.example.ui.components.PainPointsSection
import com.example.ui.components.PestDiagnosticCard
import com.example.ui.components.ProcessSection
import com.example.ui.components.ReviewsAndGallery
import com.example.ui.components.ServicesSection
import com.example.ui.components.StatsAndWhyUs
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalPrimary
import com.example.ui.theme.LimeGreenAccent
import com.example.ui.theme.PVPestControlTheme
import com.example.viewmodel.PestViewModel
import com.example.viewmodel.PestViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: PestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = PestDatabase.getDatabase(applicationContext)
        val repository = PestRepository(database.pestDao())
        val factory = PestViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[PestViewModel::class.java]

        setContent {
            PVPestControlTheme {
                MainAppScreen(viewModel = viewModel)
            }
        }
    }
}

enum class NavigationTab(val title: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector) {
    HOME("Home", Icons.Filled.Home, Icons.Outlined.Home),
    CALCULATOR("Calculator", Icons.Filled.Calculate, Icons.Outlined.Calculate),
    SERVICES("Services", Icons.Filled.MedicalServices, Icons.Outlined.MedicalServices),
    REVIEWS("Reviews", Icons.Filled.RateReview, Icons.Outlined.RateReview),
    CONTACT("Contact", Icons.Filled.ContactPage, Icons.Outlined.ContactPage),
    HISTORY("History", Icons.Filled.History, Icons.Outlined.History)
}

@Composable
fun MainAppScreen(viewModel: PestViewModel) {
    val context = LocalContext.current
    var selectedTabOrdinal by remember { mutableIntStateOf(0) }
    val currentTab = NavigationTab.entries[selectedTabOrdinal]

    val isBookingDialogOpen by viewModel.isBookingDialogOpen.collectAsStateWithLifecycle()
    val successMsg by viewModel.bookingSuccessMessage.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(successMsg) {
        successMsg?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            viewModel.clearSuccessMessage()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            HeaderAppBar(viewModel = viewModel)
        },
        bottomBar = {
            NavigationBar(
                containerColor = CharcoalPrimary,
                contentColor = Color.White,
                tonalElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .testTag("main_navigation_bar")
            ) {
                NavigationTab.entries.forEach { tab ->
                    val isSelected = tab == currentTab
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { selectedTabOrdinal = tab.ordinal },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                                contentDescription = tab.title,
                                tint = if (isSelected) CharcoalDark else Color.White.copy(alpha = 0.7f)
                            )
                        },
                        label = {
                            Text(
                                text = tab.title,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) LimeGreenAccent else Color.White.copy(alpha = 0.7f)
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = LimeGreenAccent,
                            selectedIconColor = CharcoalDark,
                            unselectedIconColor = Color.White
                        ),
                        modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.openBookingDialog() },
                containerColor = LimeGreenAccent,
                contentColor = CharcoalDark,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp),
                modifier = Modifier.testTag("fab_quick_book")
            ) {
                Icon(
                    imageVector = Icons.Default.Message,
                    contentDescription = "Quick Book Pest Inspection"
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                when (currentTab) {
                    NavigationTab.HOME -> {
                        EmergencyBanner(
                            onEmergencyCall = { viewModel.makeDirectCall(context) },
                            onEmergencyWhatsApp = { viewModel.submitBookingAndOpenWhatsApp(context) },
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                        HeroSection(
                            viewModel = viewModel,
                            onNavigateToCalculator = {
                                selectedTabOrdinal = NavigationTab.CALCULATOR.ordinal
                            }
                        )
                        PestDiagnosticCard(
                            onBookService = { pestName ->
                                viewModel.openBookingDialog(presetPest = pestName)
                            },
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                        CalculatorCard(viewModel = viewModel)
                        ServicesSection(viewModel = viewModel)
                        AMCSavingsCard(
                            onBookAMC = {
                                viewModel.openBookingDialog(presetPest = "Annual AMC Package")
                            },
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                        BeforeAfterShowcase(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                        PainPointsSection()
                        ProcessSection()
                        StatsAndWhyUs()
                        ReviewsAndGallery()
                        ContactAndFooter(viewModel = viewModel)
                    }

                    NavigationTab.CALCULATOR -> {
                        CalculatorCard(viewModel = viewModel)
                        AMCSavingsCard(
                            onBookAMC = {
                                viewModel.openBookingDialog(presetPest = "Annual AMC Package")
                            },
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                        ProcessSection()
                        ContactAndFooter(viewModel = viewModel)
                    }

                    NavigationTab.SERVICES -> {
                        ServicesSection(viewModel = viewModel)
                        PestDiagnosticCard(
                            onBookService = { pestName ->
                                viewModel.openBookingDialog(presetPest = pestName)
                            },
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                        AMCSavingsCard(
                            onBookAMC = {
                                viewModel.openBookingDialog(presetPest = "Annual AMC Package")
                            },
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                        ContactAndFooter(viewModel = viewModel)
                    }

                    NavigationTab.REVIEWS -> {
                        ReviewsAndGallery()
                        BeforeAfterShowcase(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                        StatsAndWhyUs()
                        ContactAndFooter(viewModel = viewModel)
                    }

                    NavigationTab.CONTACT -> {
                        EmergencyBanner(
                            onEmergencyCall = { viewModel.makeDirectCall(context) },
                            onEmergencyWhatsApp = { viewModel.submitBookingAndOpenWhatsApp(context) },
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                        ContactAndFooter(viewModel = viewModel)
                    }

                    NavigationTab.HISTORY -> {
                        HistorySection(viewModel = viewModel)
                        ContactAndFooter(viewModel = viewModel)
                    }
                }
            }
        }

        if (isBookingDialogOpen) {
            BookingDialog(
                viewModel = viewModel,
                onDismiss = { viewModel.closeBookingDialog() }
            )
        }
    }
}
