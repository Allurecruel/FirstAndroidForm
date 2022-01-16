package com.example.androidtask.state

import android.util.Log
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import java.util.*
import kotlin.concurrent.schedule

data class RegisterState(
    val userName: String = "",
    val password: String = "",
    val email: String = "",
    val phone: String = "",
    val isShowPassword: Boolean = false
) : MavericksState

enum class RowType {
    USER_NAME,
    PASSWORD,
    EMAIL,
    PHONE
}

class RegisterViewModel(initialState: RegisterState) : MavericksViewModel<RegisterState>(initialState) {

    fun setUserName(userName: String) = setState {
        copy(userName = userName)
    }

    fun setPassword(password: String) = setState {
        copy(password = password)
    }

    fun setEmail(email: String) = setState {
        copy(email = email)
    }

    fun setPhone(phone: String) = setState {
        copy(phone = phone)
    }

    fun setIsShowPassword() = setState {
        copy(isShowPassword = !isShowPassword)
    }

    fun onRegister() {
        register_success()
    }

    private fun register_success() {
        Timer().schedule(2000) {
            Log.d("INFO","Register success!")
        }
    }
}