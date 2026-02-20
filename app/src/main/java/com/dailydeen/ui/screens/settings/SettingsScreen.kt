package com.dailydeen.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dailydeen.ui.viewmodel.MainViewModel
import androidx.compose.ui.text.style.TextAlign
import android.app.TimePickerDialog
import java.util.Calendar


@Composable
fun SettingsScreen(navController: NavController, viewModel: MainViewModel) {
    val context = navController.context

    val isDarkMode by viewModel.isDarkMode.collectAsState()

    val notifEnabled by viewModel.notifEnabled.collectAsState()
    val notifAdhkar by viewModel.notifAdhkar.collectAsState()
    val notifChallenges by viewModel.notifChallenges.collectAsState()
    val notifQuiz by viewModel.notifQuiz.collectAsState()
    val notifHour by viewModel.notifHour.collectAsState()
    val notifMinute by viewModel.notifMinute.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SettingsCard(
                    title = "Appearance",
                    icon = Icons.Default.Palette
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Dark Mode")
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = { viewModel.setDarkMode(it) }
                        )
                    }
                }
            }

            item {
                SettingsCard(
                    title = "Notifications",
                    icon = Icons.Default.Notifications
                ) {
                    // Global toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Daily Reminders")
                        Switch(
                            checked = notifEnabled,
                            onCheckedChange = {
                                viewModel.setNotifEnabled(it)
                                viewModel.rescheduleReminders(context)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Type toggles
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Adhkars")
                        Switch(
                            checked = notifAdhkar,
                            onCheckedChange = { viewModel.setNotifAdhkar(it) },
                            enabled = notifEnabled
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Challenges")
                        Switch(
                            checked = notifChallenges,
                            onCheckedChange = { viewModel.setNotifChallenges(it) },
                            enabled = notifEnabled
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Quiz")
                        Switch(
                            checked = notifQuiz,
                            onCheckedChange = { viewModel.setNotifQuiz(it) },
                            enabled = notifEnabled
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Time picker (real)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Time")

                        TextButton(
                            enabled = notifEnabled,
                            onClick = {
                                val picker = TimePickerDialog(
                                    context,
                                    { _, selectedHour, selectedMinute ->
                                        viewModel.setNotifTime(selectedHour, selectedMinute)
                                        viewModel.rescheduleReminders(context)
                                    },
                                    notifHour,
                                    notifMinute,
                                    true // 24h format
                                )
                                picker.show()
                            }
                        ) {
                            Text(String.format("%02d:%02d", notifHour, notifMinute))
                        }
                    }

                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "DailyDeen v1.0.0",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun SettingsCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}
