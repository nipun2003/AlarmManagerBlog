package com.nipunapps.alarmmanagerblog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nipunapps.alarmmanagerblog.ui.theme.AlarmManagerBlogTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val alarmScheduler: AlarmScheduler = AlarmSchedulerImpl(this)
        var alarmItem: AlarmItem? = null
        setContent {
            AlarmManagerBlogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var secondText by remember {
                        mutableStateOf("")
                    }
                    var messageText by remember {
                        mutableStateOf("")
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(value = secondText, onValueChange = {
                            secondText = it
                        },
                            label = {
                                Text(text = "Delay Second")
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(value = messageText, onValueChange = {
                            messageText = it
                        },
                            label = {
                                Text(text = "Message")
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(onClick = {
                                alarmItem = AlarmItem(
                                    alarmTime = LocalDateTime.now().plusSeconds(
                                        secondText.toLong()
                                    ),
                                    message = messageText
                                )
                                alarmItem?.let(alarmScheduler::schedule)
                                secondText = ""
                                messageText = ""
                            }) {
                                Text(text = "Schedule")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(onClick = {
                                alarmItem?.let(alarmScheduler::cancel)
                            }) {
                                Text(text = "Cancel")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }
        }
    }
}