package com.example.swap.layout.profile.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swap.core.presentation.components.StandardButton
import com.example.swap.core.presentation.ui.theme.SwapTheme
import com.example.swap.R

@Composable
fun ProfileErrorView(
    onReloadClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(96.dp),
                painter = painterResource(id = R.drawable.ic_error),
                tint = SwapTheme.colors.barIconTintColor,
                contentDescription = "Error loading items"
            )

            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 24.dp),
                text = "Ошибка при закгрузке профиля. Попробуйте перезагрузить",
                style = SwapTheme.types.forgotText,
                color = SwapTheme.colors.headerTextColor,
                textAlign = TextAlign.Center
            )

            StandardButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Обновить",
                onClick = onReloadClick
            )
        }
    }
}

@Composable
@Preview
fun DailyViewError_Preview() {
    ProfileErrorView(onReloadClick = {})
}
