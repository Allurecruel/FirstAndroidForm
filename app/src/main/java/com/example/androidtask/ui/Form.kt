package com.example.androidtask.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.example.androidtask.R
import com.example.androidtask.state.RegisterViewModel
import com.example.androidtask.state.RowType

class Form {
    @Composable
    private fun BasicRow(
        rowType: RowType,
        placeholder: String,
        text: String,
        isShowPassword: Boolean,
        onValueChanged: (String) -> Unit,
        isShowPasswordChanged: () -> Unit
    ) {
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            OutlinedTextField(
                value = text,
                onValueChange = onValueChanged,
                shape = RoundedCornerShape(18.dp),
                visualTransformation =
                    if (rowType === RowType.PASSWORD && !isShowPassword) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },
                keyboardOptions = KeyboardOptions(keyboardType = when(rowType) {
                    RowType.USER_NAME -> KeyboardType.Text
                    RowType.PASSWORD -> KeyboardType.Password
                    RowType.EMAIL -> KeyboardType.Email
                    RowType.PHONE -> KeyboardType.Phone
                }),
                leadingIcon = {
                    Icon(
                        imageVector = when(rowType) {
                            RowType.USER_NAME -> Icons.Filled.People
                            RowType.PASSWORD -> Icons.Filled.Lock
                            RowType.EMAIL -> Icons.Filled.Email
                            RowType.PHONE -> Icons.Filled.Phone
                        },
                        contentDescription = null
                    )
                },
                trailingIcon =
                    if (rowType === RowType.PASSWORD) {
                        {
                            IconButton(onClick = isShowPasswordChanged) {
                                Icon(
                                    imageVector = if (isShowPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        }
                    } else null,
                placeholder = { Text(placeholder) }
            )
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun PopupTip(viewModel: RegisterViewModel) {
        Popup(alignment = Alignment.TopCenter) {
            AnimatedVisibility(
                visible = viewModel.collectAsState{ it.isShowMsg }.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(200.dp, 50.dp)
                        .background(Color.White.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                ) {
                    Text(viewModel.collectAsState{ it.message }.value)
                }
            }
        }
    }

    @Composable
    fun Form(viewModel: RegisterViewModel) {
        val userName: String = viewModel.collectAsState{ it.userName }.value
        val password: String = viewModel.collectAsState{ it.password }.value
        val email: String = viewModel.collectAsState{ it.email }.value
        val phone: String = viewModel.collectAsState{ it.phone }.value
        val isShowPassword: Boolean = viewModel.collectAsState{ it.isShowPassword }.value

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(bottom = 10.dp)) {
                Text("Create Account", fontSize = 20.sp, fontStyle = FontStyle.Italic)
            }

            BasicRow(RowType.USER_NAME, "User name", userName, isShowPassword, { viewModel.setUserName(it)}, { viewModel.setIsShowPassword()})
            BasicRow(RowType.PASSWORD, "Password", password, isShowPassword, { viewModel.setPassword(it)}, { viewModel.setIsShowPassword()})
            BasicRow(RowType.EMAIL, "E-mail", email, isShowPassword, { viewModel.setEmail(it)}, { viewModel.setIsShowPassword()})
            BasicRow(RowType.PHONE, "Phone", phone, isShowPassword, { viewModel.setPhone(it)}, { viewModel.setIsShowPassword()})

            Row(modifier = Modifier.padding(bottom = 15.dp, start = 200.dp)) {
                TextButton(
                    onClick = { viewModel.onRegister(userName, password, email, phone) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Login,
                        contentDescription = "Create",
                        tint = Color.Black
                    )
                    Text(text = "Create", color = Color.Black, fontStyle = FontStyle.Italic)
                }
            }
        }
    }

    @Composable
    fun App() {
        // viewModel
        val viewModel: RegisterViewModel = mavericksViewModel()

        Box(
            contentAlignment = Alignment.Center
        ) {
            // background image
            Image(
                painter = painterResource(id = R.drawable.login_background),
                contentDescription = null,
                alpha = 0.5f,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            PopupTip(viewModel)
            Form(viewModel)
        }
    }
}