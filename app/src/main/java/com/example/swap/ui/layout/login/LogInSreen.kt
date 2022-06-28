package com.example.swap.ui.layout.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.swap.R
import com.example.swap.ui.components.SButton
import com.example.swap.ui.components.STextField
import com.example.swap.ui.layout.login.models.LoginEvent
import com.example.swap.ui.layout.login.models.LoginSubState
import com.example.swap.ui.layout.login.models.LoginViewState
import com.example.swap.ui.theme.Deep_dark_blue
import com.example.swap.ui.theme.Yellow
import com.example.swap.utilities.HideKeyboard

@Composable
fun LogInScreen(navController: NavController, loginViewModel: LoginViewModel) {
    val viewState = loginViewModel.viewState.observeAsState(LoginViewState())
    val focusManager = LocalFocusManager.current
    HideKeyboard()
    with(viewState.value) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 0.dp, 20.dp, 0.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val (imageBox, editTextBox, buttonBox, textBox) = createRefs()
            when (loginSubState) {
                LoginSubState.SignIn -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp)
                            .constrainAs(imageBox) {
                                top.linkTo(parent.top, 40.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize(),
                            painter = if (isSystemInDarkTheme()) {
                                painterResource(id = R.drawable.signin_night)
                            } else {
                                painterResource(id = R.drawable.signin)
                            },
                            contentDescription = stringResource(R.string.sign_in_picture),
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                }
                LoginSubState.SignUp -> {
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
                                .fillMaxSize(),
                            painter = if (isSystemInDarkTheme()) {
                                painterResource(id = R.drawable.signup_night)
                            } else {
                                painterResource(id = R.drawable.signup)
                            },
                            contentDescription = stringResource(R.string.sign_up_picture),
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                }
                LoginSubState.Forgot -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .padding(50.dp, 0.dp, 50.dp, 0.dp)
                            .constrainAs(imageBox) {
                                top.linkTo(parent.top, 20.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize(),
                            painter = if (isSystemInDarkTheme()) {
                                painterResource(id = R.drawable.forgot_night)
                            } else {
                                painterResource(id = R.drawable.forgot)
                            },
                            contentDescription = stringResource(R.string.forgot_picture),
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                }
            }
            when (loginSubState) {
                LoginSubState.SignIn -> {
                    Box(modifier = Modifier
                        .constrainAs(editTextBox) {
                            top.linkTo(imageBox.bottom, 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(buttonBox.top, 20.dp)
                        }
                    ) {
                        Column {
                            STextField(
                                modifier = Modifier
                                    .height(75.dp)
                                    .fillMaxWidth(),
                                value = emailValue,
                                isSingleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = true,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                label = stringResource(id = R.string.email_title),
                                onValueChange = {
                                    loginViewModel.obtainEvent(LoginEvent.EmailChanged(it))
                                }
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            STextField(
                                modifier = Modifier
                                    .height(75.dp)
                                    .fillMaxWidth(),
                                value = passwordValue,
                                isSingleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = false,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                ),
                                isSecureText = true,
                                label = stringResource(id = R.string.password_title),
                                onValueChange = {
                                    loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it))
                                }
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .clickable(enabled = loginSubState != LoginSubState.Forgot,
                                        onClick = {
                                        }),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = stringResource(R.string.sign_in_to_forgot_button),
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
                LoginSubState.SignUp -> {
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
                            STextField(
                                modifier = Modifier
                                    .height(75.dp)
                                    .fillMaxWidth(),
                                value = nameValue,
                                isSingleLine = true,
                                label = stringResource(id = R.string.name_title),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    capitalization = KeyboardCapitalization.Words,
                                    autoCorrect = true,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                onValueChange = {
                                    loginViewModel.obtainEvent(LoginEvent.NameChanged(it))
                                }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            STextField(
                                modifier = Modifier
                                    .height(75.dp)
                                    .fillMaxWidth(),
                                value = emailValue,
                                isSingleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = true,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                label = stringResource(id = R.string.email_title),
                                onValueChange = {
                                    loginViewModel.obtainEvent(LoginEvent.EmailChanged(it))
                                }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            STextField(
                                modifier = Modifier
                                    .height(75.dp)
                                    .fillMaxWidth(),
                                isSingleLine = true,
                                isSecureText = true,
                                value = passwordValue,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = false,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                ),
                                label = stringResource(id = R.string.password_title),
                                onValueChange = {
                                    loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it))
                                }
                            )
                        }
                    }
                }
                LoginSubState.Forgot -> {
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
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(R.string.forgot_password_subtitle),
                                    style = MaterialTheme.typography.h2,
                                    color = if (isSystemInDarkTheme()) {
                                        Yellow
                                    } else {
                                        Deep_dark_blue
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                            STextField(
                                modifier = Modifier
                                    .height(75.dp)
                                    .fillMaxWidth(),
                                value = emailValue,
                                isSingleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    capitalization = KeyboardCapitalization.None,
                                    autoCorrect = true,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                ),
                                label = stringResource(id = R.string.email_title),
                                onValueChange = {
                                    loginViewModel.obtainEvent(LoginEvent.EmailChanged(it))
                                }
                            )
                        }
                    }
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
                SButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    text = when (loginSubState) {
                        LoginSubState.SignIn -> stringResource(R.string.sign_in_button)
                        LoginSubState.SignUp -> stringResource(R.string.sign_up_button)
                        LoginSubState.Forgot -> stringResource(R.string.forgot_button)
                    }, onClick = {

                    }
                )
            }
            Box(
                modifier = Modifier
                    .constrainAs(textBox) {
                        bottom.linkTo(parent.bottom, 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .clickable(enabled = loginSubState != LoginSubState.Forgot,
                        onClick = {
                            when (loginSubState) {
                                LoginSubState.SignIn -> loginViewModel.obtainEvent(LoginEvent.ActionClicked)
                                LoginSubState.SignUp -> loginViewModel.obtainEvent(LoginEvent.ActionClicked)
                                LoginSubState.Forgot -> Unit
                            }

                        })
            ) {
                if (loginSubState != LoginSubState.Forgot) {
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
                                text = when (loginSubState) {
                                    LoginSubState.SignIn -> stringResource(R.string.dont_have_account)
                                    LoginSubState.SignUp -> stringResource(R.string.already_have_account)
                                    LoginSubState.Forgot -> ""
                                },
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
                                text = when (loginSubState) {
                                    LoginSubState.SignIn -> stringResource(R.string.sign_in_to_sign_up_button)
                                    LoginSubState.SignUp -> stringResource(R.string.sign_up_to_sign_in_button)
                                    LoginSubState.Forgot -> ""
                                },
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
    }
}