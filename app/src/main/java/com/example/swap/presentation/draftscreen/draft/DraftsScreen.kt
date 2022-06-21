package com.example.swap.presentation.draftscreen.draft

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.swap.R
import com.example.swap.domain.models.Advert
import com.example.swap.objects.Constants.SIGN_IN_TYPE_ANONYMOUS
import com.example.swap.objects.Response
import com.example.swap.presentation.profilescreen.viewmodels.UserViewModel
import com.example.swap.ui.theme.*
import com.example.swap.utilities.HideKeyboard
import com.example.swap.utilities.showToast

@Composable
fun DraftsScreen(navController: NavController, userViewModel: UserViewModel) {
    val context = LocalContext.current
    val message = stringResource(id = R.string.profile_screen_message)
    HideKeyboard()
    userViewModel.getUserInfo()
    when (val response = userViewModel.getUserData.value) {
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {
            if (response.data != null) {
                val user = response.data
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
                    /*if (drafts.isNotEmpty()) {
                        *//*LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f),
            ) {
                items(drafts.size) { id ->
                    DraftItem(advert = drafts[id])
                }
            }*//*
        } else {*/
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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Button(
                            onClick = {
                                if (user.signInType != SIGN_IN_TYPE_ANONYMOUS)
                                    navController.navigate("new_advert")
                                else {
                                    showToast(context = context, message = message)
                                }
                            },
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
                                text = stringResource(R.string.create_advert_button),
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
        }
        is Response.Error -> {

        }
    }
}


@Composable
fun DraftItem(advert: Advert) {
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
                text = advert.title,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.offset(10.dp, 0.dp),
                color = if (isSystemInDarkTheme()) {
                    Color.White
                } else {
                    Deep_dark_blue
                }
            )
            Text(
                text = advert.description,
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
