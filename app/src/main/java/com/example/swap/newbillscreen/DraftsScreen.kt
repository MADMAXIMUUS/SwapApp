package com.example.swap.newbillscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.swap.R
import com.example.swap.ui.theme.*
import com.example.swap.utilities.HideKeyboard
import com.lopatasoftware.swap.data.remote.responses.Bill

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraftsScreen(drafts: List<Bill>, navController: NavController) {
    HideKeyboard()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 55.dp)
    ) {
        Text(
            text = stringResource(R.string.drafts),
            modifier = Modifier
                .offset(20.dp, 3.dp)
                .padding(2.dp),
            color = if (isSystemInDarkTheme()) {
                Yellow
            } else {
                Deep_dark_blue
            },
            style = MaterialTheme.typography.h3
        )
        if (drafts.isNotEmpty()) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f),
            ) {
                items(drafts.size) { id ->
                    DraftItem(bill = drafts[id])
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.7f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.dont_have_drafts_message),
                    style = MaterialTheme.typography.body1
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Button(
                onClick = { navController.navigate("new_bill") },
                colors = ButtonDefaults
                    .buttonColors(
                        backgroundColor = Light_brown
                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = stringResource(R.string.create_bill_button),
                    color = if (isSystemInDarkTheme()) {
                        Dark_Background
                    } else {
                        Color.White
                    }
                )
            }
        }
    }
}

@Composable
fun DraftItem(bill: Bill) {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .offset(0.dp, 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_owl_search),
                contentDescription = stringResource(R.string.product_photo),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(6.dp))
                    .height(90.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = bill.title,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.offset(10.dp, 0.dp),
                color = if (isSystemInDarkTheme()) {
                    Color.White
                } else {
                    Deep_dark_blue
                }
            )
            Text(
                text = bill.description,
                modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 10.dp),
                style = MaterialTheme.typography.body2,
                color = if (isSystemInDarkTheme()) {
                    Color(0xFFC4C4C2)
                } else {
                    Night_blue
                }
            )
        }
    }
}
