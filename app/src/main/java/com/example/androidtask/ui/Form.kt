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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidtask.R

enum class RowType {
    USER_NAME,
    PASSWORD,
    EMAIL,
    PHONE
}

class Form {
    @Composable
    private fun BasicRow(rowType: RowType, placeholder: String) {
        var text by remember { mutableStateOf("") }
        var isShowPassword by remember { mutableStateOf(false) }

        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
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

    @Preview
    @Composable
    fun Form() {
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
            BasicRow(rowType = RowType.USER_NAME, placeholder = "User name")
            BasicRow(rowType = RowType.PASSWORD, placeholder = "Password")
            BasicRow(rowType = RowType.EMAIL, placeholder = "E-mail")
            BasicRow(rowType = RowType.PHONE, placeholder = "Phone")
            Row(modifier = Modifier.padding(bottom = 15.dp, start = 200.dp)) {
                Text("Create", fontSize = 15.sp, fontStyle = FontStyle.Italic, modifier = Modifier.padding(vertical = 15.dp))
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Login,
                        contentDescription = null
                    )
                }
            }
        }
    }
}