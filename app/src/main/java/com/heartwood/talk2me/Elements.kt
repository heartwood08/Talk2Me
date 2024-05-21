package com.heartwood.talk2me

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import coil.compose.rememberAsyncImagePainter
import com.heartwood.talk2me.dataclass.Chat
import com.heartwood.talk2me.dataclass.Contact
import com.heartwood.talk2me.dataclass.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.Year
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class Elements {
    companion object {
        @OptIn(ExperimentalFoundationApi::class)
        @Composable
        fun ChatCard(clickOnChat: (nameFriend: String) -> Unit, msg: Chat) {
            val isContextMenuVisible = remember { mutableStateOf(false) }
            Surface(
                modifier = Modifier
                    .combinedClickable(
                        onClick = {
                            clickOnChat(msg.sender)
                        },
                        onLongClick = {
                            isContextMenuVisible.value = true
                        }
                    )
                    .fillMaxWidth(),
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            if (msg.imageUrl.isNotEmpty()) {
                                msg.imageUrl
                            } else {
                                R.drawable.ic_person_24
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = msg.sender,
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = msg.content,
                            modifier = Modifier.padding(top = 4.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                }
                if (isContextMenuVisible.value) {
                    Popup(
                        alignment = Alignment.TopCenter,
                        onDismissRequest = { isContextMenuVisible.value = false }
                    ) {
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                                .size(150.dp),
                        ) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f)
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.Center),
                                        text = msg.sender
                                    )
                                }
                                Divider()
                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f)
                                    .clickable { }) {
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.Center),
                                        text = "Usuń"
                                    )
                                }
                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f)
                                    .clickable { }) {
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.Center),
                                        text = "Zablokuj"
                                    )
                                }
                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f)
                                    .clickable { }) {
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.Center),
                                        text = "Przypnij"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        @Composable
        fun ContactCard(msg: Contact) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 5.dp
            ) {
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            if (msg.imageUrl.isNotEmpty()) {
                                msg.imageUrl
                            } else {
                                R.drawable.ic_person_24
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = msg.name,
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = msg.email,
                            modifier = Modifier.padding(top = 4.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        @SuppressLint("MutableCollectionMutableState")
        @Composable
        fun MessageList(nameFriend: String, messages: List<Message>) {
            var newMessagesCount by remember { mutableStateOf(0) }
            var listState = rememberLazyListState(messages.lastIndex)
            val coroutineScope = rememberCoroutineScope()
            var message by remember { mutableStateOf("") }
            LocalSoftwareKeyboardController.current
            val mutableMessage by remember { mutableStateOf(messages.toMutableList()) }
            Column(
                modifier = Modifier
                .padding(start = 5.dp, bottom = 5.dp)
            ) {
                Row {
                    Text(
                        text = nameFriend,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(mutableMessage.sortedBy { it.timestamp }) { item ->
                            MessageView(item.sender, item.content, item.timestamp)
                        }
                    }
                    if (newMessagesCount > 0) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    delay(100)
                                    listState.animateScrollToItem(index = messages.lastIndex)
                                }
                                newMessagesCount = 0
                            },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("Nowe wiadomości: $newMessagesCount")
                        }
                    }
                }
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = message,
                        onValueChange = {
                            message = it
                            coroutineScope.launch {
                                listState.animateScrollToItem(index = messages.lastIndex)
                            }
                        },
                        label = { Text("Wiadomość") },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(onSend = {
                            mutableMessage.add(
                                Message(
                                    "test@test.pl",
                                    message,
                                    System.currentTimeMillis()
                                )
                            )
                            message = ""
                        }),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                    )
                }
            }
        }
        @Composable
        fun MessageView(sender: String, content: String, timestamp: Long) {
            val backgroundColor =
                if (sender == "test@test.pl") MaterialTheme.colorScheme.primary else Color.LightGray
            val contentColor = if (sender == "test@test.pl") Color.White else Color.Black
            val alignment =
                if (sender == "test@test.pl") Alignment.CenterEnd else Alignment.CenterStart
            val timeString = timestampToTime(timestamp)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = alignment
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = backgroundColor,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                ) {
                    Column() {
                        Text(
                            text = content,
                            color = contentColor
                        )
                        Text(
                            text = timeString,
                            color = contentColor,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
        @Composable
        fun AddContact(onDismiss: () -> Unit){
            var name by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var nameError by remember {
                mutableStateOf(false)
            }
            var emailError by remember {
                mutableStateOf(false)
            }
            Dialog(
                onDismissRequest = { }
            ) {
                Surface(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Start),
                            text = "Dodaj kontakt:"
                        )
                        OutlinedTextField(
                            isError = nameError,
                            value = name,
                            onValueChange = {
                                nameError = false
                                name = it
                                            },
                            label = { Text("Imię") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(
                                onNext = { }
                            )
                        )
                        OutlinedTextField(
                            isError = emailError,
                            value = email,
                            onValueChange = {
                                emailError = false
                                email = it
                                            },
                            label = { Text("Email") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    //onContactSaved(name, email)
                                    name = ""
                                    email = ""
                                    onDismiss()
                                }
                            )
                        )
                        Button(
                            onClick = {
                                if(name.isNotEmpty() && email.isNotEmpty()) {  // Save contact when Save button is clicked
                                    // onContactSaved(imie, email)
                                    // Clear fields
                                    name = ""
                                    email = ""
                                    // Dismiss dialog
                                    onDismiss()
                                }
                                else if(name.isEmpty() && email.isEmpty()) {
                                    nameError = true
                                    emailError = true
                                }
                                else if(email.isEmpty()){
                                    emailError = true
                                }
                                else{
                                    nameError = true
                                }
                            }
                        ) {
                            Text("Zapisz")
                        }
                    }
                }
            }
        }

        private fun timestampToTime(timestamp: Long): String {
            val instant = Instant.ofEpochMilli(timestamp)
            val time = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            return time.format(formatter)
        }
    }
}

