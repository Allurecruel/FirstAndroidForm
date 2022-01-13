package com.example.androidtask

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class BasicApp {

    @Composable
    private fun Greeting(name: String) {
        var expanded by remember { mutableStateOf(false) }
        val extraPadding = if (expanded) 48.dp else 0.dp

        Surface(
            color = MaterialTheme.colors.primary
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)) {
                    Text("Hello, ")
                    Text("$name")
                }
                OutlinedButton(onClick = { expanded = !expanded }) {
                    Text(text = if (expanded) "Show More" else "Show Less")
                }
            }
        }
    }

    @Composable
    private fun Greetings() {
        val names: List<String> = listOf("World", "Compose")
        Column() {
            for (name in names) {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Greeting(name = name)
                }
            }
        }
    }

    @Composable
    private fun OnboardingScreen(onContinueClicked: () -> Unit) {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Welcome to the Basics Codelab!")
                Button(
                    modifier = Modifier.padding(vertical = 24.dp),
                    onClick = onContinueClicked
                ) {
                    Text("Continue")
                }
            }
        }
    }

    @Composable
    fun MyApp() {
        var shouldShowOnboard by remember { mutableStateOf(true) }

        if (shouldShowOnboard) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboard = false })
        } else {
            Greetings()
        }
    }
}