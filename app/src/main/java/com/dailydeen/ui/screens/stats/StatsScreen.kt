package com.dailydeen.ui.screens.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dailydeen.ui.viewmodel.MainViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.entry.entryModelOf
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.lazy.items


@Composable
fun StatsScreen(navController: NavController, viewModel: MainViewModel) {
    val weeklyProgress by viewModel.weeklyProgress.collectAsState()
    val today = weeklyProgress.lastOrNull()
    val starsToday = today?.starsEarned ?: 0
    val challengesToday = today?.challengesCompleted ?: 0
    val quizzesToday = today?.quizzesCompleted ?: 0
    val streak = today?.streakCount ?: 0

    val weeklyStars = weeklyProgress.sumOf { it.starsEarned }
    val weeklyChallenges = weeklyProgress.sumOf { it.challengesCompleted }
    val weeklyQuizzes = weeklyProgress.sumOf { it.quizzesCompleted }

    val badges = listOf(
        Badge("⭐ First Star", "Earn at least 1 star today", starsToday >= 1),
        Badge("✅ First Challenge", "Complete at least 1 challenge today", challengesToday >= 1),
        Badge("🧠 First Quiz", "Complete at least 1 quiz today", quizzesToday >= 1),
        Badge("🔥 3-Day Streak", "Keep a streak of 3 days", streak >= 3),
        Badge("🔥 7-Day Streak", "Keep a streak of 7 days", streak >= 7),
        Badge("⭐ Star Collector", "Earn 50 stars in 7 days", weeklyStars >= 50),
        Badge("✅ Challenge Champ", "Complete 20 challenges in 7 days", weeklyChallenges >= 20),
        Badge("🧠 Quiz Master", "Complete 5 quizzes in 7 days", weeklyQuizzes >= 5),
    )

    val starsList = if (weeklyProgress.isEmpty()) listOf(0f,0f,0f,0f,0f,0f,0f)
    else weeklyProgress.map { it.starsEarned.toFloat() }
    val chartEntryModel = entryModelOf(*starsList.toTypedArray())

    val dateFormatter = SimpleDateFormat("EEE", Locale.getDefault())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        Text(
            text = "Statistics",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Your progress over the last 7 days",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Stars Earned",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Chart(
                            chart = columnChart(),
                            model = chartEntryModel,
                            startAxis = rememberStartAxis(),
                            bottomAxis = rememberBottomAxis(
                                valueFormatter = { value, _ ->
                                    val index = value.toInt()
                                    if (index in weeklyProgress.indices) {
                                        dateFormatter.format(Date(weeklyProgress[index].date))
                                    }else {
                                        // fallback si weeklyProgress vide
                                        listOf("Mon","Tue","Wed","Thu","Fri","Sat","Sun").getOrNull(index) ?: ""
                                    }
                                }
                            ),
                            modifier = Modifier.height(200.dp)
                        )
                    }
                }
            }

            item {
                val currentStreak = weeklyProgress.lastOrNull()?.streakCount ?: 0
                val totalChallenges = weeklyProgress.sumOf { it.challengesCompleted }
                val totalQuizzes = weeklyProgress.sumOf { it.quizzesCompleted }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    StatCard(
                        title = "Current Streak",
                        value = "🔥 $currentStreak",
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Challenges (7d)",
                        value = "$totalChallenges",
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Quizzes (7d)",
                        value = "$totalQuizzes",
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Text(
                    text = "Badges",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            items(badges) { badge ->
                BadgeRow(badge = badge)
            }


            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    StatCard(
                        title = "Total Stars",
                        value = "${weeklyProgress.sumOf { it.starsEarned }}",
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Avg Stars/Day",
                        value = "${if (weeklyProgress.isNotEmpty()) weeklyProgress.sumOf { it.starsEarned } / weeklyProgress.size else 0}",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            
            item {
                StatCard(
                    title = "Challenges Completed",
                    value = "${weeklyProgress.sumOf { it.challengesCompleted }}",
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                StatCard(
                    title = "Quizzes Completed",
                    value = "${weeklyProgress.sumOf { it.quizzesCompleted }}",
                    modifier = Modifier.fillMaxWidth()
                )
            }


            item {
                Text(
                    text = "Daily Breakdown",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            items(weeklyProgress) { day ->
                val label = dateFormatter.format(Date(day.date))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = label, fontWeight = FontWeight.Bold)

                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Text(text = "⭐ ${day.starsEarned}")
                            Text(text = "✅ ${day.challengesCompleted}")
                            Text(text = "🧠 ${day.quizzesCompleted}")
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }

}
@Composable
fun BadgeRow(badge: Badge) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (badge.isUnlocked)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = badge.title, fontWeight = FontWeight.Bold)
                Text(text = if (badge.isUnlocked) "Unlocked ✅" else "Locked 🔒")
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = badge.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
            )
        }
    }
}


data class Badge(
    val title: String,
    val description: String,
    val isUnlocked: Boolean
)

