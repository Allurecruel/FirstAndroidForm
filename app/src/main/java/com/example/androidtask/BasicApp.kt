package com.example.androidtask

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore

class BasicApp {

    @Composable
    private fun Greeting(name: String) {
        var expanded by remember { mutableStateOf(false) }
        val extraPadding by animateDpAsState(
            targetValue = if (expanded) 48.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp)
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                    Text("Hello, ")
                    Text(text = "$name",
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    ))
                    if (expanded) {
                        Text(
                            text = ("Composeme ipsumcolorsit lazy, padding theme dlit, sed do bouncy. ").repeat(4)
                        )
                    }
                }
                IconButton(onClick = { expanded = !expanded}) {
                    Icon(
                        imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore,
                        contentDescription = if (expanded) {
                            stringResource(R.string.show_less)
                        } else {
                            stringResource(R.string.show_more)
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun Greetings() {
        val names: List<String> = List(1000){ "$it" }
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = names) {
                name -> Greeting(name = name)
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
        var shouldShowOnboard by rememberSaveable  { mutableStateOf(true) }

        if (shouldShowOnboard) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboard = false })
        } else {
            Greetings()
        }
    }
}