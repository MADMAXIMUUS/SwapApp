package com.example.swap.layout.profile

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.swap.layout.profile.models.ProfileEvent
import com.example.swap.layout.profile.models.ProfileViewState
import com.example.swap.layout.profile.views.ProfileErrorView

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel,
    userId: String
) {
    val viewState = profileViewModel.profileViewState.collectAsState()
    Scaffold(topBar = {

    }) {
        when (val state = viewState.value) {
            is ProfileViewState.Display -> {

            }
            ProfileViewState.Error -> ProfileErrorView(onReloadClick = {

            })
            ProfileViewState.Loading -> {}
            ProfileViewState.NoLogin -> {}
        }
    }


    /*userViewModel.getUserInfo(userId)
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
    }*/

    LaunchedEffect(key1 = viewState, block = {
        profileViewModel.obtainEvent(event = ProfileEvent.EnterScreen(userId))
    })
}

@Composable
fun ProfileItem() {
    /*Card(
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
                text = "??????????????",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.offset(10.dp, 0.dp),
                color = if (isSystemInDarkTheme()) {
                    Color.White
                } else {
                    Deep_dark_blue
                }
            )
            Text(
                text = "?????????????? ??????????????",
                modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 10.dp),
                style = MaterialTheme.typography.body2,
                color = if (isSystemInDarkTheme()) {
                    Color(0xFFC4C4C2)
                } else {
                    Night_blue
                }
            )
        }
    }*/
}