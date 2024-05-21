package com.heartwood.talk2me.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.heartwood.talk2me.Elements
import com.heartwood.talk2me.dataclass.Chat

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    clickOnChat: (nameFriend: String) -> Unit
) {
    val chat = listOf(
        Chat(
            "test 1",
            "text text text",
            ""
        ),
        Chat(
            "test 2",
            "text text text",
            ""
        ),
        Chat("test 3", "text text text", "")
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(chat.sortedBy { it.sender }) { item ->
            Elements.ChatCard(clickOnChat, item)
        }
    }
}

