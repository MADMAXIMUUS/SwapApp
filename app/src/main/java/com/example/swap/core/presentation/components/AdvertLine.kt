package com.example.swap.core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.swap.R
import com.example.swap.core.domain.models.AdvertCardItem
import com.example.swap.core.presentation.ui.theme.SwapTheme
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdvertLine(
    advert: AdvertCardItem,
    onAdvertClick: () -> Unit = {}
) {
    Card(
        elevation = 3.dp,
        shape = SwapTheme.shapes.swapShape,
        backgroundColor = SwapTheme.colors.cardBackgroundColor,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp),
        onClick = {
            onAdvertClick()
        }
    ) {
        Row(modifier = Modifier.height(100.dp)) {
            GlideImage(
                imageModel = advert.imageUrl,
                contentDescription = stringResource(R.string.product_photo),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(130.dp)
                    .padding(10.dp)
                    .clip(SwapTheme.shapes.swapShape),
            )
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, end = 10.dp)
            ) {
                Text(
                    text = advert.title,
                    modifier = Modifier
                        .padding(top = 5.dp),
                    style = SwapTheme.types.cardHeaderText,
                    color = SwapTheme.colors.cardHeaderTextColor
                )
                Text(
                    text = advert.description,
                    style = SwapTheme.types.cardDescriptionText,
                    maxLines = 3,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth(),
                    color = SwapTheme.colors.cardDescriptionTextColor,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = "Адрес: " + advert.address,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    style = SwapTheme.types.cardAddressText,
                    color = SwapTheme.colors.cardAddressTextColor
                )
            }
        }
    }
}