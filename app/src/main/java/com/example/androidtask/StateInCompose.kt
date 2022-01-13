package com.example.androidtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class StateInCompose {

    @Composable
    private fun Greeting(name: String) {
        val expanded = remember { mutableStateOf(false) }
        val extraPadding = if (expanded.value) 48.dp else 0.dp

        Surface(
            color = MaterialTheme.colors.primary
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(modifier = Modifier.weight(1f).padding(bottom = extraPadding)) {
                    Text("Hello, ")
                    Text("$name")
                }
                OutlinedButton(onClick = { expanded.value = !expanded.value }) {
                    Text(text = if (expanded.value) "Show More" else "Show Less")
                }
            }
        }
    }

    @Composable
    fun MyApp() {
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
}