package com.example.swap.feature_advert.presentation.create_and_edit_advert

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NewAdvertScreen(
    navController: NavController,
    newAdvertViewModel: NewAdvertViewModel
) {
   /* HideKeyboard()
    val advertTitle = remember { mutableStateOf("") }
    val viewModel: TagViewModel = hiltViewModel()
    val advertDescription = remember { mutableStateOf("") }
    val tags = remember { mutableStateListOf<String>() }
    val cameraPermissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA
    )
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        LazyRow {
            item {
                AddPhotoCard(cameraPermissionState, navController)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow {
            item {
                AddTagButton(tags, viewModel)
                Spacer(modifier = Modifier.width(8.dp))
            }
            items(tags.size) {
                TagAdvertScreen(tags[it])
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            modifier = Modifier
                .height(75.dp)
                .fillMaxWidth(),
            value = advertTitle.value,
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
                    Corn
                } else {
                    Deep_dark_blue
                },
                focusedLabelColor = if (isSystemInDarkTheme()) {
                    Corn
                } else {
                    Deep_dark_blue
                },
                focusedIndicatorColor = if (isSystemInDarkTheme()) {
                    Corn
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
                advertTitle.value = it
            },
            label = {
                Text(
                    text = stringResource(R.string.enter_title_hint),
                    fontSize = 22.sp
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.7f),
            value = advertDescription.value,
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
                    Corn
                } else {
                    Deep_dark_blue
                },
                focusedLabelColor = if (isSystemInDarkTheme()) {
                    Corn
                } else {
                    Deep_dark_blue
                },
                focusedIndicatorColor = if (isSystemInDarkTheme()) {
                    Corn
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
                advertDescription.value = it
            },
            label = {
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
                onClick = {
                    if (advertTitle.value.isEmpty())
                        showToast("Title can't be empty", context)
                    else
                        advertViewModel
                            .createAdvert(
                                title = advertTitle.value,
                                description = advertDescription.value,
                                tags = tags,
                                images = emptyList()
                            )
                },
                colors = ButtonDefaults
                    .buttonColors(
                        backgroundColor = Meteor
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
                        Not_Black
                    } else {
                        Color.White
                    }
                )
                if (advertViewModel.createAdvertData.value != Response.Success(null))
                    when (val response = advertViewModel.createAdvertData.value) {
                        is Response.Loading -> {
                            CircularProgressIndicator()
                        }
                        is Response.Success -> {
                            if (response.data!!) {
                                LaunchedEffect(key1 = true) {
                                    navController.navigate("home") {
                                        popUpTo("new_advent") {
                                            inclusive = true
                                        }
                                    }
                                }
                            } else {
                                showToast("Error creating ad! Try later.", context)
                            }
                        }
                        is Response.Error -> {
                            showToast(response.message, context)
                        }
                    }
            }
        }
    }*/
}


@Composable
fun PhotoCard() {
    /*Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(0.dp, 5.dp, 5.dp, 0.dp)
            .clickable(
                enabled = true,
                onClick = {

                }
            ),
        shape = RoundedCornerShape(10.dp),
        backgroundColor =
        if (isSystemInDarkTheme()) {
            Gray
        } else {
            Color.White
        },
        border = BorderStroke(
            color = if (isSystemInDarkTheme()) {
                Gray
            } else {
                Color.LightGray
            },
            width = 1.dp
        )
    ) {

    }*/
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddPhotoCard(cameraPermissionState: PermissionState, navController: NavController) {
    /*Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(0.dp, 5.dp, 5.dp, 0.dp)
            .clickable {
                cameraPermissionState.launchPermissionRequest()
                navController.navigate("camera")
            },
        shape = RoundedCornerShape(10.dp),
        backgroundColor =
        if (isSystemInDarkTheme()) {
            Gray
        } else {
            Color.White
        },
        border = BorderStroke(
            color = if (isSystemInDarkTheme()) {
                Gray
            } else {
                Color.LightGray
            },
            width = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_change_photo_big),
                contentDescription = "Add photo button icon",
                tint = if (isSystemInDarkTheme()) {
                    Corn
                } else {
                    Deep_dark_blue
                },
                modifier = Modifier
                    .height(80.dp)
                    .width(60.dp)
            )
            Text(
                text = stringResource(R.string.add_photo),
                style = MaterialTheme.typography.subtitle2,
                color = if (isSystemInDarkTheme()) {
                    Corn
                } else {
                    Deep_dark_blue
                }
            )
        }
    }*/
}

@Composable
fun AddTagButton(tags: SnapshotStateList<String>, viewModel: TagViewModel) {
    /*val alert = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .clickable(
                enabled = true,
                onClick = {
                    alert.value = true
                }
            ),
        border = BorderStroke(
            color = if (isSystemInDarkTheme()) {
                Gray
            } else {
                Color.LightGray
            },
            width = 1.dp
        ),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = if (isSystemInDarkTheme()) {
            Gray
        } else {
            Color.White
        }
    ) {
        Icon(
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .padding(8.dp),
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = "Add tag Button",
            tint = if (isSystemInDarkTheme()) {
                Corn
            } else {
                Deep_dark_blue
            }
        )
    }
    if (alert.value) {
        CustomDialog(alert = alert, tags, viewModel)
    }*/
}

@Composable
fun TagAdvertScreen(text: String) {
    /*Card(
        modifier = Modifier
            .height(40.dp)
            .clickable(
                enabled = true,
                onClick = {

                }
            ),
        border = BorderStroke(
            color = if (isSystemInDarkTheme()) {
                Gray
            } else {
                Color.LightGray
            },
            width = 1.dp
        ),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = if (isSystemInDarkTheme()) {
            Gray
        } else {
            Color.White
        }
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp, 4.dp, 8.dp, 8.dp),
            text = text,
            style = MaterialTheme.typography.h2,
            color = if (isSystemInDarkTheme()) {
                Corn
            } else {
                Deep_dark_blue
            }
        )
    }*/
}

@Composable
fun CustomDialog(
    alert: MutableState<Boolean>,
    tags: SnapshotStateList<String>,
    viewModel: TagViewModel
) {
    /*viewModel.getAllTags()
    val response = viewModel.tagsData.value
    val tag = remember { mutableStateOf("") }
    tag.value = ""
    val focusManager = LocalTextInputService.current
    val tagsTemp = remember { mutableStateListOf<String>() }
    tagsTemp.clear()
    tagsTemp.addAll(tags)
    Dialog(
        onDismissRequest = { alert.value = false }
    ) {
        Card(
            modifier = Modifier
                .width(400.dp)
                .height(600.dp),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = if (isSystemInDarkTheme()) {
                Dark_Bar
            } else {
                Color.White
            }
        ) {
            when (response) {
                is Response.Loading -> {
                    CircularProgressIndicator()
                }
                is Response.Success -> {
                    val firebaseTags = response.data
                    Spacer(modifier = Modifier.height(15.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            OutlinedTextField(
                                modifier = Modifier
                                    .height(65.dp)
                                    .fillMaxWidth(),
                                value = tag.value,
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
                                        Corn
                                    } else {
                                        Deep_dark_blue
                                    },
                                    focusedLabelColor = if (isSystemInDarkTheme()) {
                                        Corn
                                    } else {
                                        Deep_dark_blue
                                    },
                                    focusedIndicatorColor = if (isSystemInDarkTheme()) {
                                        Corn
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
                                    autoCorrect = true,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        if (tag.value.isNotEmpty() && !firebaseTags
                                                .any { it.text == tag.value }
                                        ) {
                                            viewModel.addTag(tag.value)
                                            tagsTemp.add(tag.value)
                                            tag.value = ""
                                            focusManager?.hideSoftwareKeyboard()
                                        }
                                    }),
                                textStyle = MaterialTheme.typography.body1,
                                singleLine = true,
                                onValueChange = {
                                    tag.value = it
                                },
                                label = {
                                    Text(
                                        text = "Тэг",
                                        fontSize = 20.sp
                                    )
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(0.7f)
                        ) {
                            items(firebaseTags.size) {
                                if (tagsTemp.contains(firebaseTags[it].text))
                                    TagItem(firebaseTags[it].text, true, tagsTemp)
                                else
                                    TagItem(firebaseTags[it].text, false, tagsTemp)
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    alert.value = false
                                    tags.clear()
                                    tags.addAll(tagsTemp)
                                },
                                colors = ButtonDefaults
                                    .buttonColors(
                                        backgroundColor = Meteor
                                    ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                            ) {
                                Text(
                                    text = "Add tags",
                                    style = MaterialTheme.typography.button,
                                    color = if (isSystemInDarkTheme()) {
                                        Not_Black
                                    } else {
                                        Color.White
                                    }
                                )
                            }
                        }
                    }
                }
                is Response.Error -> {

                }
            }
        }
    }*/
}

@Composable
fun TagItem(tag: String, isChecked: Boolean, tags: SnapshotStateList<String>) {
    /*val checked = remember { mutableStateOf(isChecked) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .padding(8.dp, 0.dp)
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = {
                checked.value = it
                if (it)
                    tags.add(tag)
                else
                    tags.remove(tag)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = if (isSystemInDarkTheme()) {
                    Corn
                } else {
                    Deep_dark_blue
                },
                uncheckedColor = if (isSystemInDarkTheme()) {
                    Corn
                } else {
                    Deep_dark_blue
                },
                checkmarkColor = if (isSystemInDarkTheme()) {
                    Dark_Bar
                } else {
                    White
                }
            )
        )
        Text(
            text = tag,
            style = MaterialTheme.typography.h2,
            color = if (isSystemInDarkTheme()) {
                Corn
            } else {
                Deep_dark_blue
            }
        )
    }*/
}