package com.example.swap.layout.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController
) {
    /*viewModel.getAllAdverts()
    val context = LocalContext.current
    when (val response = viewModel.advertsData.value) {
        is Response.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color = if (isSystemInDarkTheme()) {
                        Corn
                    } else {
                        Deep_dark_blue
                    }
                )
            }
        }
        is Response.Success -> {
            val adverts = response.data
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 55.dp)
            ) {
                items(adverts.size) { id ->
                    HomeLineItem(advert = adverts[id])
                }
            }
        }
        is Response.Error -> {
            showToast("Все плохо", context)
        }
    }
    *//*if (!mode) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 55.dp),
        ) {
            items(10) { id ->
                HomeSmallItem(advert = adverts[id])

            }
        }
    } else {

    }*/
}