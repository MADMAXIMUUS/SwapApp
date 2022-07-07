package com.example.swap.feature_auth.presentation.signup

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.swap.R
import com.example.swap.core.presentation.components.StandardButton
import com.example.swap.core.presentation.components.StandardTextField
import com.example.swap.core.presentation.ui.theme.SwapTheme
import com.example.swap.core.presentation.util.UiEvent
import com.example.swap.core.presentation.util.asString
import com.example.swap.core.util.Constants
import com.example.swap.core.util.Screen
import com.example.swap.feature_auth.presentation.util.AuthError
import kotlinx.coroutines.flow.collectLatest


@ExperimentalComposeUiApi
@Composable
fun SignUpScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    onSignUp: () -> Unit = {},
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val nameState = viewModel.nameState.value
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val signUpState = viewModel.signUpState.value
    val context = LocalContext.current

    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    keyboardController?.hide()
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                is UiEvent.Navigate -> {
                    onNavigate(event.route)
                }
                UiEvent.NavigateUp -> {}
                UiEvent.OnLogin -> {
                    onSignUp()
                }
            }
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 0.dp, 20.dp, 0.dp)
            .background(color = SwapTheme.colors.primaryBackgroundColor)
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
                StandardTextField(
                    modifier = Modifier
                        .height(96.dp)
                        .fillMaxWidth(),
                    text = nameState.text,
                    hint = stringResource(id = R.string.name_title),
                    error = when (viewModel.nameState.value.error) {
                        is AuthError.FieldEmpty -> {
                            stringResource(id = R.string.error_field_empty)
                        }
                        is AuthError.InputTooShort -> {
                            stringResource(
                                id = R.string.input_too_short,
                                Constants.MIN_USERNAME_LENGTH
                            )
                        }
                        else -> ""
                    },
                    onValueChange = {
                        viewModel.onEvent(SignUpEvent.EnteredName(it))
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text
                    )
                )
                StandardTextField(
                    modifier = Modifier
                        .height(96.dp)
                        .fillMaxWidth(),
                    text = emailState.text,
                    hint = stringResource(id = R.string.email_title),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email
                    ),
                    error = when (emailState.error) {
                        is AuthError.FieldEmpty -> {
                            stringResource(id = R.string.error_field_empty)
                        }
                        is AuthError.InvalidEmail -> {
                            stringResource(id = R.string.not_a_valid_email)
                        }
                        else -> ""
                    },
                    onValueChange = {
                        viewModel.onEvent(SignUpEvent.EnteredEmail(it))
                    }
                )
                StandardTextField(
                    modifier = Modifier
                        .height(106.dp)
                        .fillMaxWidth(),
                    text = passwordState.text,
                    hint = stringResource(id = R.string.password_title),
                    error = when (passwordState.error) {
                        is AuthError.FieldEmpty -> {
                            stringResource(id = R.string.error_field_empty)
                        }
                        is AuthError.InputTooShort -> {
                            stringResource(
                                id = R.string.input_too_short,
                                Constants.MIN_PASSWORD_LENGTH
                            )
                        }
                        is AuthError.InvalidPassword -> {
                            stringResource(id = R.string.invalid_password)
                        }
                        else -> ""
                    },
                    onValueChange = {
                        viewModel.onEvent(SignUpEvent.EnteredPassword(it))
                    },
                    onPasswordToggleClick = {
                        viewModel.onEvent(SignUpEvent.TogglePasswordVisibility)
                    },
                    isPasswordVisible = passwordState.isPasswordVisible,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Password
                    )
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
            StandardButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(shape = SwapTheme.shapes.swapShape),
                text = stringResource(
                    R.string.sign_up_button
                ), onClick = {
                    viewModel.onEvent(SignUpEvent.SignUp)
                },
                progressIndicator = signUpState.isLoading
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(textBox) {
                    bottom.linkTo(parent.bottom, 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clickable(
                    onClick = {
                        onNavigate(
                            Screen.SignInScreen.route
                        )
                    }
                )
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
                        text = stringResource(
                            R.string.already_have_account
                        ),
                        style = SwapTheme.types.hintActionText,
                        color = SwapTheme.colors.textFieldTextColor
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
                        style = SwapTheme.types.actionText,
                        color = SwapTheme.colors.textFieldTextColor
                    )
                }
            }
        }
    }
}