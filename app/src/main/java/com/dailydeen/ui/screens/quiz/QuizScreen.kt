package com.dailydeen.ui.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import kotlinx.coroutines.delay

@Composable
fun QuizScreen(navController: NavController, viewModel: MainViewModel) {
    val questions by viewModel.dailyQuiz.collectAsState()
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }

    if (questions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (showResult) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "🎉", style = MaterialTheme.typography.displayLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Quiz Finished!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(text = "You scored $score out of ${questions.size}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Back to Home")
            }
        }
        return
    }

    val currentQuestion = questions[currentQuestionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        Text(text = "Daily Quiz", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(text = "Question ${currentQuestionIndex + 1} of ${questions.size}", style = MaterialTheme.typography.bodySmall)
        
        Spacer(modifier = Modifier.height(24.dp))

        // Chat-like message for the question
        ChatBubble(
            text = currentQuestion.question,
            isFromUser = false
        )

        if (isCorrect != null) {
            Spacer(modifier = Modifier.height(12.dp))
            ChatBubble(
                text = if (isCorrect == true) "Correct! 🌟" else "Not quite. ${currentQuestion.explanation}",
                isFromUser = false,
                containerColor = if (isCorrect == true) Color(0xFF4CAF50).copy(alpha = 0.2f) else Color(0xFFF44336).copy(alpha = 0.2f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Options
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            currentQuestion.options.forEachIndexed { index, option ->
                val isSelected = selectedOptionIndex == index
                val buttonColor = when {
                    isSelected && isCorrect == true -> Color(0xFF4CAF50)
                    isSelected && isCorrect == false -> Color(0xFFF44336)
                    else -> MaterialTheme.colorScheme.surfaceVariant
                }

                Button(
                    onClick = {
                        if (selectedOptionIndex == null) {
                            selectedOptionIndex = index
                            isCorrect = index == currentQuestion.correctAnswerIndex
                            if (isCorrect == true) score++
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor,
                        contentColor = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    enabled = selectedOptionIndex == null
                ) {
                    Text(text = option, modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }

        if (selectedOptionIndex != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (currentQuestionIndex < questions.size - 1) {
                        currentQuestionIndex++
                        selectedOptionIndex = null
                        isCorrect = null
                    } else {
                        viewModel.completeQuiz()
                        showResult = true
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(if (currentQuestionIndex < questions.size - 1) "Next Question" else "See Results")
            }
        }
    }
}

@Composable
fun ChatBubble(
    text: String,
    isFromUser: Boolean,
    containerColor: Color? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isFromUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = containerColor ?: if (isFromUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (isFromUser) 16.dp else 0.dp,
                bottomEnd = if (isFromUser) 0.dp else 16.dp
            ),
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp),
                color = if (isFromUser) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}
