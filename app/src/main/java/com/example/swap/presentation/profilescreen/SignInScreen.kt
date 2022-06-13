package com.example.swap.presentation.profilescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.swap.R
import com.example.swap.objects.Response
import com.example.swap.presentation.profilescreen.viewmodels.AuthenticationViewModel
import com.example.swap.ui.theme.Black
import com.example.swap.ui.theme.Deep_dark_blue
import com.example.swap.ui.theme.Light_brown
import com.example.swap.ui.theme.Yellow
import com.example.swap.utilities.HideKeyboard
import com.example.swap.utilities.ShowToast

@Composable
fun SignInScreen(navController: NavController, authViewModel: AuthenticationViewModel) {
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
                    painterResource(id = R.drawable.signin_night)
                } else {
                    painterResource(id = R.drawable.signin)
                },
                contentDescription = stringResource(R.string.register_picture),
                contentScale = ContentScale.FillBounds,
            )
        }
        Box(modifier = Modifier
            .constrainAs(editTextBox) {
                top.linkTo(imageBox.bottom, 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(buttonBox.top, 20.dp)
            }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .height(75.dp)
                        .fillMaxWidth(),
                    value = nameState.value,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = if (isSystemInDarkTheme()) {
                            Color.White
                        } else {
                            Deep_dark_blue
                        },
                        backgroundColor = if (isSystemInDarkTheme()) {
                            Color(0xFF323232)
                        } else {
                            Color.White
                        },
                        unfocusedLabelColor = if (isSystemInDarkTheme()) {
                            Yellow
                        } else {
                            Deep_dark_blue
                        },
                        focusedLabelColor = if (isSystemInDarkTheme()){
                            Yellow
                        } else {
                            Deep_dark_blue
                        },
                        focusedIndicatorColor = if (isSystemInDarkTheme()) {
                            Yellow
                        } else {
                            Deep_dark_blue
                        },
                        unfocusedIndicatorColor = if (isSystemInDarkTheme()) {
                            if (nameState.value == "") {
                                Color(0xFF323232)
                            } else {
                                Yellow
                            }
                        } else {
                            if (nameState.value == "") {
                                Color.LightGray
                            } else {
                                Deep_dark_blue
                            }
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Sentences,
                        autoCorrect = true
                    ),
                    textStyle = MaterialTheme.typography.h2,
                    singleLine = true,
                    onValueChange = {
                        nameState.value = it
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.name_title),
                            fontSize = 22.sp
                        )
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .height(75.dp)
                        .fillMaxWidth(),
                    value = emailState.value,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = if (isSystemInDarkTheme()) {
                            Color.White
                        } else {
                            Deep_dark_blue
                        },
                        backgroundColor = if (isSystemInDarkTheme()) {
                            Color(0xFF323232)
                        } else {
                            Color.White
                        },
                        unfocusedLabelColor = if (isSystemInDarkTheme()) {
                            Yellow
                        } else {
                            Deep_dark_blue
                        },
                        focusedLabelColor = if (isSystemInDarkTheme()){
                            Yellow
                        } else {
                            Deep_dark_blue
                        },
                        focusedIndicatorColor = if (isSystemInDarkTheme()) {
                            Yellow
                        } else {
                            Deep_dark_blue
                        },
                        unfocusedIndicatorColor = if (isSystemInDarkTheme()) {
                            if (emailState.value == "") {
                                Color(0xFF323232)
                            } else {
                                Yellow
                            }
                        } else {
                            if (emailState.value == "") {
                                Color.LightGray
                            } else {
                                Deep_dark_blue
                            }
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true
                    ),
                    textStyle = MaterialTheme.typography.h2,
                    singleLine = true,
                    onValueChange = {
                        emailState.value = it
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.email_title),
                            fontSize = 22.sp
                        )
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .height(75.dp)
                        .fillMaxWidth(),
                    value = passwordState.value,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = if (isSystemInDarkTheme()) {
                            Color.White
                        } else {
                            Deep_dark_blue
                        },
                        backgroundColor = if (isSystemInDarkTheme()) {
                            Color(0xFF323232)
                        } else {
                            Color.White
                        },
                        unfocusedLabelColor = if (isSystemInDarkTheme()) {
                            Yellow
                        } else {
                            Deep_dark_blue
                        },
                        focusedLabelColor = if (isSystemInDarkTheme()){
                            Yellow
                        } else {
                            Deep_dark_blue
                        },
                        focusedIndicatorColor = if (isSystemInDarkTheme()) {
                            Yellow
                        } else {
                            Deep_dark_blue
                        },
                        unfocusedIndicatorColor = if (isSystemInDarkTheme()) {
                            if (passwordState.value == "") {
                                Color(0xFF323232)
                            } else {
                                Yellow
                            }
                        } else {
                            if (passwordState.value == "") {
                                Color.LightGray
                            } else {
                                Deep_dark_blue
                            }
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true
                    ),
                    textStyle = MaterialTheme.typography.h2,
                    singleLine = true,
                    onValueChange = {
                        passwordState.value = it
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.password_title),
                            fontSize = 22.sp
                        )
                    },
                    visualTransformation = PasswordVisualTransformation()
                )
            }
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
                    authViewModel.signIn(
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
                if (authViewModel.signInState.value != Response.Success(null))
                when (val response = authViewModel.signInState.value) {
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
                            ShowToast(stringResource(R.string.creating_error))
                        }
                    }
                    is Response.Error -> {
                        ShowToast(response.message)
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
                        text = stringResource(R.string.log_in_title_2),
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