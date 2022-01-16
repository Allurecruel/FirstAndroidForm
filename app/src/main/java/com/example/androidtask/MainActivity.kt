package com.example.androidtask

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.mvrx.Mavericks
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