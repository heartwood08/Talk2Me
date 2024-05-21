package com.heartwood.talk2me.screen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.heartwood.talk2me.Elements.Companion.MessageList
import com.heartwood.talk2me.dataclass.Message


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(
    nameFriend: String
) {
    val messages = listOf(
        Message("test1@test.com", "text", 1653330137731L),
        Message("test2@test.com", "text2", 1653330197731L)
    )
    MessageList(nameFriend = nameFriend,messages = messages)
}
