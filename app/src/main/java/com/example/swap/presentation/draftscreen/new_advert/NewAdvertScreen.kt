package com.example.swap.presentation.draftscreen.new_advert

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swap.R
import com.example.swap.presentation.profilescreen.viewmodels.AuthenticationViewModel
import com.example.swap.ui.theme.Dark_Background
import com.example.swap.ui.theme.Deep_dark_blue
import com.example.swap.ui.theme.Light_brown
import com.example.swap.ui.theme.Yellow
import com.example.swap.utilities.HideKeyboard


@Composable
fun NewAdvertScreen(
    navController: NavController,
    viewModel: AdvertViewModel,
    authViewModel: AuthenticationViewModel
) {
    HideKeyboard()
    val billTitle = remember { mutableStateOf("") }
    val billDescription = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyRow {
            item {
                AddPhotoCard()
            }
            items(5) {
                PhotoCard()
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(),
            value = billTitle.value,
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
                placeholderColor = if (isSystemInDarkTheme()) {
                    Color.White
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
                billTitle.value = it
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.new_bill_enter_name_hint),
                    fontSize = 22.sp
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.7f),
            value = billDescription.value,
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
                placeholderColor = if (isSystemInDarkTheme()) {
                    Color.White
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
            textStyle = MaterialTheme.typography.body1,
            singleLine = false,
            onValueChange = {
                billDescription.value = it
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.new_bill_enter_description_hint),
                    fontSize = 20.sp
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
        ) {
            Button(
                onClick = { navController.navigate("home") },
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

@Composable
fun PhotoCard() {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(5.dp, 0.dp, 5.dp, 0.dp)
    ) {

    }
}

@Composable
fun AddPhotoCard() {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(1.dp, 0.dp, 5.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_change_photo_big),
                contentDescription = "Add photo button icon",
                modifier = Modifier
                    .height(50.dp)
                    .width(40.dp)
            )
            Text(
                text = stringResource(R.string.add_photo),
                style = MaterialTheme.typography.h3
            )
        }
    }
}