package com.example.swap.presentation.chatscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swap.R
import com.example.swap.ui.theme.Deep_dark_blue
import com.example.swap.ui.theme.Night_blue
import com.example.swap.ui.theme.White
import com.example.swap.utilities.HideKeyboard

@Composable
fun ChatsScreen(navController: NavController) {
    HideKeyboard()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 55.dp)
    ) {
        items(10) {
            ChatItem(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatItem(navController: NavController) {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp, 6.dp, 10.dp, 6.dp),
        onClick = {

        }
    ) {
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_owl_search),
                contentDescription = stringResource(R.string.product_photo),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.7f)
            ) {
                Text(
                    text = "Исмаил"/*chat.companionId*/,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.offset(10.dp, 0.dp),
                    color = if (isSystemInDarkTheme()) {
                        Color.White
                    } else {
                        Deep_dark_blue
                    }
                )
                Text(
                    text = "Вы:" + "chat.lastMessage",
                    Modifier
                        .padding(10.dp, 0.dp, 10.dp, 10.dp),
                    style = MaterialTheme.typography.body2,
                    color = if (isSystemInDarkTheme()) {
                        Color(0xFFC4C4C2)
                    } else {
                        Night_blue
                    }
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(
                            if (10 < 99) {
                                DpSize(30.dp, 30.dp)
                            } else {
                                DpSize(40.dp, 30.dp)
                            }
                        )
                        .background(
                            color = if (isSystemInDarkTheme()) {
                                Color.Gray
                            } else {
                                Color.LightGray
                            }
                        )
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = 10.toString(),
                        fontSize = 18.sp,
                        color = if (isSystemInDarkTheme()) {
                            White
                        } else {
                            Night_blue
                        }
                    )
                }
            }
        }
    }
}
