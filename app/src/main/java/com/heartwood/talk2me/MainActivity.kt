package com.heartwood.talk2me

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.heartwood.talk2me.screen.MainApp
import com.heartwood.talk2me.ui.theme.Talk2MeTheme


class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Talk2MeTheme {
                MainApp()
            }
        }
    }
}