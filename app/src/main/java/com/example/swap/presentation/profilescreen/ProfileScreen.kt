package com.example.swap.presentation.profilescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.swap.R
import com.example.swap.objects.Response
import com.example.swap.presentation.profilescreen.viewmodels.AuthenticationViewModel
import com.example.swap.presentation.profilescreen.viewmodels.UserViewModel
import com.example.swap.ui.theme.*
import com.example.swap.utilities.HideKeyboard
import com.example.swap.utilities.showToast
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthenticationViewModel,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current
    HideKeyboard()
    userViewModel.getUserInfo()
    when (val response = userViewModel.getUserData.value) {
        is Response.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color = if (isSystemInDarkTheme()) {
                        Yellow
                    } else {
                        Deep_dark_blue
                    }
                )
            }
        }
        is Response.Error -> {
            showToast(message = stringResource(R.string.profile_loading_error), context = context)
        }
        is Response.Success -> {
            if (authViewModel.isUserAuthenticated()) {
                if (response.data != null) {
                    val user = response.data
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp, 0.dp, 0.dp, 55.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        ConstraintLayout(
                            modifier = Modifier
                                .padding(20.dp, 20.dp, 20.dp, 0.dp)
                                .fillMaxWidth()
                        ) {
                            val (image, name, ratingBox, settingsButton) = createRefs()
                            GlideImage(
                                imageModel = user.avatarUrl,
                                contentDescription = "Profile avatar",
                                shimmerParams = ShimmerParams(
                                    baseColor = if (isSystemInDarkTheme()) {
                                        Dark_Background
                                    } else {
                                        Color.LightGray
                                    },
                                    highlightColor = if (isSystemInDarkTheme()) {
                                        Yellow
                                    } else {
                                        Deep_dark_blue
                                    },
                                    durationMillis = 350,
                                    dropOff = 0.65f,
                                    tilt = 20f
                                ),
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        brush = if (isSystemInDarkTheme()) {
                                            SolidColor(Yellow)
                                        } else {
                                            SolidColor(Color.LightGray)
                                        },
                                        shape = CircleShape
                                    )
                                    .constrainAs(image) {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                    }
                            )
                            Text(
                                text = user.name,
                                color = if (isSystemInDarkTheme()) {
                                    Yellow
                                } else {
                                    Night_blue
                                },
                                style = MaterialTheme.typography.h1,
                                modifier = Modifier
                                    .constrainAs(name) {
                                        top.linkTo(parent.top, 8.dp)
                                        start.linkTo(image.end, 8.dp)
                                    }
                            )
                            Row(modifier = Modifier
                                .constrainAs(ratingBox) {
                                    top.linkTo(name.bottom, 4.dp)
                                    start.linkTo(name.start)
                                }
                            ) {
                                Icon(
                                    painter = painterResource(
                                        id = when (user.rating) {
                                            0.0 -> {
                                                R.drawable.ic_rating_outline
                                            }
                                            0.5 -> {
                                                R.drawable.ic_rating_outline_half
                                            }
                                            else -> R.drawable.ic_rating_color
                                        }
                                    ),
                                    contentDescription = "Profile avatar",
                                    modifier = Modifier
                                        .size(30.dp),
                                    tint = if (isSystemInDarkTheme()) {
                                        Light_brown
                                    } else {
                                        Deep_dark_blue
                                    }
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    painter = painterResource(
                                        id = when (user.rating) {
                                            1.0, 0.0, 0.5 -> {
                                                R.drawable.ic_rating_outline
                                            }
                                            1.5 -> {
                                                R.drawable.ic_rating_outline_half
                                            }
                                            else -> R.drawable.ic_rating_color
                                        }
                                    ),
                                    contentDescription = "Profile avatar",
                                    modifier = Modifier
                                        .size(30.dp),
                                    tint = if (isSystemInDarkTheme()) {
                                        Light_brown
                                    } else {
                                        Deep_dark_blue
                                    }
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    painter = painterResource(
                                        id = when (user.rating) {
                                            2.0, 1.0, 0.0, 0.5, 1.5 -> {
                                                R.drawable.ic_rating_outline
                                            }
                                            2.5 -> {
                                                R.drawable.ic_rating_outline_half
                                            }
                                            else -> R.drawable.ic_rating_color
                                        }
                                    ),
                                    contentDescription = "Profile avatar",
                                    modifier = Modifier
                                        .size(30.dp),
                                    tint = if (isSystemInDarkTheme()) {
                                        Light_brown
                                    } else {
                                        Deep_dark_blue
                                    }
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    painter = painterResource(
                                        id = when (user.rating) {
                                            3.0, 2.0, 1.0, 0.0, 0.5, 1.5, 2.5 -> {
                                                R.drawable.ic_rating_outline
                                            }
                                            3.5 -> {
                                                R.drawable.ic_rating_outline_half
                                            }
                                            else -> R.drawable.ic_rating_color
                                        }
                                    ),
                                    contentDescription = "Profile avatar",
                                    modifier = Modifier
                                        .size(30.dp),
                                    tint = if (isSystemInDarkTheme()) {
                                        Light_brown
                                    } else {
                                        Deep_dark_blue
                                    }
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    painter = painterResource(
                                        id = when (user.rating) {
                                            4.0, 3.0, 2.0, 1.0, 0.0, 0.5, 1.5, 2.5, 3.5 -> {
                                                R.drawable.ic_rating_outline
                                            }
                                            4.5 -> {
                                                R.drawable.ic_rating_outline_half
                                            }
                                            else -> R.drawable.ic_rating_color
                                        }
                                    ),
                                    contentDescription = "Profile avatar",
                                    modifier = Modifier
                                        .size(30.dp),
                                    tint = if (isSystemInDarkTheme()) {
                                        Light_brown
                                    } else {
                                        Deep_dark_blue
                                    }
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.ic_settings),
                                contentDescription = "Profile avatar",
                                modifier = Modifier
                                    .size(30.dp)
                                    .constrainAs(settingsButton) {
                                        top.linkTo(parent.top)
                                        end.linkTo(parent.end)
                                    },
                                tint = Light_brown
                            )
                        }
                        Text(
                            text = stringResource(R.string.drafts),
                            modifier = Modifier
                                .offset(20.dp, 8.dp)
                                .padding(2.dp),
                            color = if (isSystemInDarkTheme()) {
                                Yellow
                            } else {
                                Deep_dark_blue
                            },
                            style = MaterialTheme.typography.h3
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.8f)
                        ) {
                            items(user.adverts.size) { it ->
                                ProfileItem()
                            }
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = stringResource(R.string.profile_screen_message),
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 10.dp, 20.dp, 0.dp),
                    ) {
                        Button(
                            onClick = { navController.navigate("logIn") },
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
                                text = stringResource(R.string.log_in_title),
                                style = MaterialTheme.typography.button,
                                color = if (isSystemInDarkTheme()) {
                                    Dark_Background
                                } else {
                                    Color.White
                                }
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                    ) {
                        Button(
                            onClick = { navController.navigate("signIn") },
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
                                text = stringResource(R.string.register_title),
                                style = MaterialTheme.typography.button,
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
    }
}

@Composable
fun ProfileItem() {
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
                text = "Подушка",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.offset(10.dp, 0.dp),
                color = if (isSystemInDarkTheme()) {
                    Color.White
                } else {
                    Deep_dark_blue
                }
            )
            Text(
                text = "Хорошая подушка",
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