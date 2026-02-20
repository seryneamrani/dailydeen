package com.dailydeen.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dailydeen.DailyDeenApp
import com.dailydeen.ui.screens.adhkars.AdhkarsScreen
import com.dailydeen.ui.screens.challenges.ChallengesScreen
import com.dailydeen.ui.screens.home.HomeScreen
import com.dailydeen.ui.screens.quiz.QuizScreen
import com.dailydeen.ui.screens.settings.SettingsScreen
import com.dailydeen.ui.screens.stats.StatsScreen
import com.dailydeen.ui.theme.DailyDeenTheme
import com.dailydeen.ui.viewmodel.MainViewModel
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Request notification permission on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!granted) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }

        val app = application as DailyDeenApp

        val viewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app.repository, app.dataStoreManager) as T
            }
        }

        setContent {
            val viewModel: MainViewModel = viewModel(factory = viewModelFactory)
            val isDarkMode by viewModel.isDarkMode.collectAsState()

            DailyDeenTheme(darkTheme = isDarkMode) {
                MainApp(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(viewModel: MainViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { navController.navigate("home") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Challenges") },
                    label = { Text("Challenges") },
                    selected = false,
                    onClick = { navController.navigate("challenges") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Adhkars") },
                    label = { Text("Adhkars") },
                    selected = false,
                    onClick = { navController.navigate("adhkars") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Quiz") },
                    label = { Text("Quiz") },
                    selected = false,
                    onClick = { navController.navigate("quiz") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Stats") },
                    label = { Text("Stats") },
                    selected = false,
                    onClick = { navController.navigate("stats") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = false,
                    onClick = { navController.navigate("settings") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(navController, viewModel) }
            composable("challenges") { ChallengesScreen(navController, viewModel) }
            composable("adhkars") { AdhkarsScreen(navController, viewModel) }
            composable("quiz") { QuizScreen(navController, viewModel) }
            composable("stats") { StatsScreen(navController, viewModel) }
            composable("settings") { SettingsScreen(navController, viewModel) }
        }
    }
}
