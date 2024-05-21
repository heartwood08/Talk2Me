package com.heartwood.talk2me.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.heartwood.talk2me.Elements
import com.heartwood.talk2me.Elements.Companion.ContactCard
import com.heartwood.talk2me.dataclass.Contact


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactsScreen() {
    var dialogOpen by remember { mutableStateOf(false) }
    val contacts = listOf(
        Contact(
            "test 1",
            "test1@test.pl",
            ""
        ),
        Contact(
            "test 2",
            "test2.test.pl",
            ""
        ),
        Contact("test 3", "test3@test.pl", "")
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(contacts.sortedBy { it.name }) { item ->
            ContactCard(item)
        }
    }
    if (dialogOpen) {
        Elements.AddContact(
//            onContactSaved = { imie, email ->
//                kontaktList = kontaktList + Pair(imie, email)
//            },
            onDismiss = { dialogOpen = false }
        )
    }
}


