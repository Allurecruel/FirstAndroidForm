package com.example.androidtask.state

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.schedule

data class RegisterState(
    var userName: String = "",
    var password: String = "",
    var email: String = "",
    var phone: String = ""
)

enum class RowType {
    USER_NAME,
    PASSWORD,
    EMAIL,
    PHONE
}

class RegisterViewModel()
    : ViewModel() {
    var registerState by mutableStateOf<RegisterState>(RegisterState())

    fun onRegister(registerState: RegisterState) {
        this.registerState = registerState
        Log.d("INFO","userName: ${registerState.userName}")
        Log.d("INFO","password: ${registerState.password}")
        Log.d("INFO","email: ${registerState.email}")
        Log.d("INFO","phone: ${registerState.phone}")
        register_success()
    }

    private fun register_success() {
        Timer().schedule(2000) {
            Log.d("INFO","Register success!")
        }
    }

    private fun register_failed() {
        Timer().schedule(2000) {
            Log.d("INFO","Register failed!")
        }
    }
}