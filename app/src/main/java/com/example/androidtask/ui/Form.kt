package com.example.androidtask.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidtask.R
import com.example.androidtask.state.RegisterState
import com.example.androidtask.state.RegisterViewModel
import com.example.androidtask.state.RowType

class Form {
    @Composable
    private fun BasicRow(rowType: RowType, placeholder: String, text: String, onValueChanged: (String) -> Unit) {
        var isShowPassword by remember { mutableStateOf(false) }

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
                        IconButton(onClick = { isShowPassword = !isShowPassword }) {
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

    @Composable
    fun Form(viewModel: RegisterViewModel) {
        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }

        // background image
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = null,
            alpha = 0.5f,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(bottom = 10.dp)) {
                Text("Create Account", fontSize = 20.sp, fontStyle = FontStyle.Italic)
            }
            BasicRow(RowType.USER_NAME, "User name", userName) { userName = it }
            BasicRow(RowType.PASSWORD, "Password", password) { password = it }
            BasicRow(RowType.EMAIL, "E-mail", email) { email = it }
            BasicRow(RowType.PHONE, "Phone", phone) { phone = it }
            Row(modifier = Modifier.padding(bottom = 15.dp, start = 200.dp)) {
                Text("Create", fontSize = 15.sp, fontStyle = FontStyle.Italic, modifier = Modifier.padding(vertical = 15.dp))
                IconButton(onClick = { viewModel.onRegister(RegisterState(userName, password, email, phone)) }) {
                    Icon(
                        imageVector = Icons.Filled.Login,
                        contentDescription = null
                    )
                }
            }
        }
    }
}