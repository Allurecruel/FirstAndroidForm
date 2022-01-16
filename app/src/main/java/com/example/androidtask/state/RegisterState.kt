package com.example.androidtask.state

import android.telephony.PhoneNumberUtils
import android.text.TextUtils
import android.util.Patterns
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import java.util.*
import java.util.regex.Pattern
import kotlin.concurrent.schedule


data class RegisterState(
    // info
    val userName: String = "",
    val password: String = "",
    val email: String = "",
    val phone: String = "",

    // isShowPassword switch
    val isShowPassword: Boolean = false,

    // top alert popup
    val message: String = "",
    val isShowMsg: Boolean = false
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

    private fun setMessage(message: String) = setState {
        copy(message = message)
    }

    private fun setIsShowMsg(isShowMsg: Boolean) = setState {
        copy(isShowMsg = isShowMsg)
    }

    // business logic
    fun onRegister(userName: String, password: String, email: String, phone: String) {
        if (!validate("Invalid UserName!") { isValidUserName(userName) }) {
            return
        }

        if (!validate("Invalid Password!") { isValidPassword(password) }) {
            return
        }

        if (!validate("Invalid Email!") { isValidEmail(email) }) {
            return
        }

        if (!validate("Invalid Phone!") { isValidPhone(phone) }) {
            return
        }

        Timer().schedule(1000) {
            showMsg("Register successfully!")
        }
    }

    private fun isValidUserName(userName: String?): Boolean {
        return !TextUtils.isEmpty(userName) &&
                Pattern.compile("^[A-Za-z0-9]\\w{5,29}$").matcher(userName).matches()
    }

    private fun isValidPassword(password: String?) : Boolean {
        //should contain at least one digit
        //should contain at least one special char
        //should contain at least one upper case
        //should contain at least 8 from the mentioned characters
        return !TextUtils.isEmpty(password) &&
                Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$").matcher(password).matches()
    }

    private fun isValidEmail(email: String?): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPhone(phone: String?): Boolean {
        return !TextUtils.isEmpty(phone) &&
                PhoneNumberUtils.isGlobalPhoneNumber(phone)
    }

    private fun validate(message: String, validation: () -> Boolean): Boolean {
        if (!validation()) {
            showMsg(message)
            return false
        }
        return true
    }

    private fun showMsg(message: String) {
        setIsShowMsg(true)
        setMessage(message)
        Timer().schedule(4000) {
            setIsShowMsg(false)
        }
    }
}