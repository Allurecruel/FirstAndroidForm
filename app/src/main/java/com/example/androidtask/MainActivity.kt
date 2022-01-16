package com.example.androidtask

import BasicsCodelabTheme
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.MavericksView
import com.example.androidtask.state.RegisterViewModel
import com.example.androidtask.ui.Form

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mavericks.initialize(this)
        setContent {
            Form().App()
        }
    }
}