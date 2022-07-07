package com.example.swap.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun AdvertSquare(
    advert: AdvertCardItem,
    onAdvertClick: () -> Unit = {}
) {
    Card(
        elevation = 3.dp,
        shape = SwapTheme.shapes.cardTagShape,
        backgroundColor = SwapTheme.colors.cardBackgroundColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onClick = {
            onAdvertClick()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            GlideImage(
                imageModel = advert.imageUrl,
                contentDescription = stringResource(R.string.product_photo),
                modifier = Modifier
                    .clip(
                        shape = SwapTheme.shapes.squareCardPhotoShape
                    )
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = advert.title,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                style = SwapTheme.types.cardHeaderText,
                color = SwapTheme.colors.cardHeaderTextColor
            )
            Text(
                text = advert.description,
                style = SwapTheme.types.cardDescriptionText,
                maxLines = 6,
                modifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 10.dp)
                    .fillMaxWidth()
                    .weight(1f),
                color = SwapTheme.colors.cardDescriptionTextColor,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
            )
            Text(
                text = "Адрес: " + advert.address,
                style = SwapTheme.types.cardAddressText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, top=5.dp),
                color = SwapTheme.colors.cardAddressTextColor,
                textAlign = TextAlign.Center
            )
        }
    }
}