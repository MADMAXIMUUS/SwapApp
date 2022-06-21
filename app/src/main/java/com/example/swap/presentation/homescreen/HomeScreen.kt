package com.example.swap.presentation.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.swap.R
import com.example.swap.domain.models.Advert
import com.example.swap.objects.Response
import com.example.swap.presentation.draftscreen.new_advert.AdvertViewModel
import com.example.swap.ui.theme.Deep_dark_blue
import com.example.swap.ui.theme.Night_blue
import com.example.swap.utilities.HideKeyboard
import com.example.swap.utilities.showToast
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeScreen(
    mode: Boolean,
    navController: NavHostController,
    viewModel: AdvertViewModel
) {
    HideKeyboard()
    viewModel.getAllAdverts()
    val context = LocalContext.current
    when (val response = viewModel.advertsData.value) {
        is Response.Loading -> {
            CircularProgressIndicator()
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
    /*if (!mode) {
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

@Composable
fun HomeSmallItem(advert: Advert) {
    Card(
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
                text = advert.title,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.offset(10.dp, 5.dp),
                color = if (isSystemInDarkTheme()) {
                    Color.White
                } else {
                    Deep_dark_blue
                }
            )
            Text(
                text = advert.description,
                Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
                style = MaterialTheme.typography.body2,
                color = if (isSystemInDarkTheme()) {
                    Color(0xFFC4C4C2)
                } else {
                    Night_blue
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeLineItem(advert: Advert) {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp),
        onClick = {

        }
    ) {
        Row(modifier = Modifier.height(100.dp)) {
            GlideImage(
                imageModel = if (advert.imageListUrls.isNotEmpty()) advert.imageListUrls[0] else "empty",
                contentDescription = stringResource(R.string.product_photo),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(110.dp)
                    .width(110.dp)
                    .offset(10.dp, 10.dp)
                    .clip(RoundedCornerShape(10.dp)),
            )
            Column(modifier = Modifier.offset(20.dp, 20.dp)) {
                Text(
                    text = advert.title,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.offset(10.dp, 0.dp),
                    color = if (isSystemInDarkTheme()) {
                        Color.White
                    } else {
                        Deep_dark_blue
                    }
                )
                Text(
                    text = advert.description,
                    Modifier.padding(10.dp, 0.dp, 10.dp, 10.dp),
                    style = MaterialTheme.typography.body2,
                    color = if (isSystemInDarkTheme()) {
                        Color(0xFFC4C4C2)
                    } else {
                        Night_blue
                    }
                )
            }
        }
    }
}