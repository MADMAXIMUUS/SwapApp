package com.example.swap.profilescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.swap.R
import com.example.swap.ui.theme.Black
import com.example.swap.ui.theme.Deep_dark_blue
import com.example.swap.ui.theme.Light_brown
import com.example.swap.ui.theme.Yellow
import com.example.swap.utilities.HideKeyboard

@Composable
fun SignInScreen(navController: NavController) {
    HideKeyboard()
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 0.dp, 20.dp, 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (imageBox, editTextBox, buttonBox, textBox) = createRefs()
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
                    value = name.value,
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
                            Color(0xFF323232)
                        } else {
                            Color.LightGray
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
                        name.value = it
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
                    value = email.value,
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
                            Color(0xFF323232)
                        } else {
                            Color.LightGray
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
                        email.value = it
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
                    value = password.value,
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
                            Color(0xFF323232)
                        } else {
                            Color.LightGray
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
                        password.value = it
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.password_title),
                            fontSize = 22.sp
                        )
                    }
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
                onClick = { navController.navigate("profile") },
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
            }
        }
        Box(modifier = Modifier
            .constrainAs(textBox) {
                bottom.linkTo(parent.bottom, 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
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