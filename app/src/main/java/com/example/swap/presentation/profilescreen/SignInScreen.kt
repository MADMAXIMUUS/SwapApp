package com.example.swap.presentation.profilescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.swap.ui.layout.login.LoginViewModel
import com.example.swap.ui.theme.Black
import com.example.swap.ui.theme.Deep_dark_blue
import com.example.swap.ui.theme.Light_brown
import com.example.swap.ui.theme.Yellow
import com.example.swap.utilities.HideKeyboard
import com.example.swap.utilities.showToast

@Composable
fun SignInScreen(navController: NavController, loginViewModel: LoginViewModel) {
    val context = LocalContext.current
    HideKeyboard()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 0.dp, 20.dp, 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (imageBox, editTextBox, buttonBox, textBox) = createRefs()
        val nameState = remember { mutableStateOf("") }
        val emailState = remember { mutableStateOf("") }
        val passwordState = remember { mutableStateOf("") }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(40.dp, 0.dp, 40.dp, 0.dp)
                .constrainAs(imageBox) {
                    top.linkTo(parent.top, 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 1.dp,
                        brush = if (isSystemInDarkTheme()) {
                            SolidColor(Yellow)
                        } else {
                            SolidColor(Color.LightGray)
                        },
                        shape = RoundedCornerShape(10.dp)
                    ),
                painter = if (isSystemInDarkTheme()) {
                    painterResource(id = R.drawable.signup_night)
                } else {
                    painterResource(id = R.drawable.signup)
                },
                contentDescription = stringResource(R.string.sign_up_picture),
                contentScale = ContentScale.FillBounds,
            )
        }
        Box(
            modifier = Modifier
                .padding(20.dp, 10.dp, 20.dp, 10.dp)
                .constrainAs(buttonBox) {
                    bottom.linkTo(textBox.top, 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Button(
                onClick = {
                    loginViewModel.signInEmail(
                        nameState.value,
                        emailState.value,
                        passwordState.value
                    )
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
                    text = stringResource(id = R.string.register_title),
                    color = if (isSystemInDarkTheme()) {
                        Black
                    } else {
                        Color.White
                    },
                    style = MaterialTheme.typography.button
                )
                if (loginViewModel.signInState.value != Response.Success(null))
                    when (val response = loginViewModel.signInState.value) {
                        is Response.Loading -> {
                            CircularProgressIndicator()
                        }
                        is Response.Success -> {
                            if (response.data!!) {
                                LaunchedEffect(key1 = true) {
                                    navController.navigate("profile") {
                                        popUpTo("signIn") {
                                            inclusive = true
                                        }
                                }
                            }
                        } else {
                            showToast(stringResource(R.string.creating_error), context)
                        }
                    }
                    is Response.Error -> {
                        showToast(response.message, context)
                    }
                }
            }
        }
        Box(modifier = Modifier
            .constrainAs(textBox) {
                bottom.linkTo(parent.bottom, 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .clickable(enabled = true,
                onClick = {
                    navController.navigate("logIn") {
                        launchSingleTop = true
                    }
                })
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.already_have_account),
                        style = MaterialTheme.typography.h3,
                        color = if (isSystemInDarkTheme()) {
                            Yellow
                        } else {
                            Deep_dark_blue
                        }
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.sign_up_to_sign_in_button),
                        style = MaterialTheme.typography.h2,
                        color = if (isSystemInDarkTheme()) {
                            Yellow
                        } else {
                            Deep_dark_blue
                        }
                    )
                }
            }
        }
    }
}