package com.syhan.cinemasearch.core.presentation

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.syhan.cinemasearch.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = resources.getColor(R.color.transparent, null),
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = resources.getColor(R.color.white, null),
                darkScrim = resources.getColor(R.color.white, null)
            )
        )
        setContentView(R.layout.activity_main)
    }
}
