package com.example.swap.profilescreen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.swap.R
import com.example.swap.ui.theme.Dark_Background
import com.example.swap.ui.theme.Light_brown
import com.example.swap.utilities.HideKeyboard

@Composable
fun ProfileScreen(navController: NavController) {
    HideKeyboard()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 55.dp),
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