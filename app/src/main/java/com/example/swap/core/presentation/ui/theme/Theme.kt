package com.example.swap.core.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object SwapTheme {
    val colors: SwapColors
        @Composable
        get() = LocalSwapColors.current

    val types: SwapTypes
        @Composable
        get() = LocalSwapTypes.current

    val shapes: SwapShapes
        @Composable
        get() = LocalSwapShapes.current

    val spacing: SwapSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSwapSpacing.current
}


@Composable
fun SwapTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) {
        SwapDarkColorPalette
    } else {
        SwapLightColorPalette
    }

    val types = SwapTypes(
        topBarTitleText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 26.sp
        ),
        bottomBarText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontSize = 12.sp
        ),
        bottomBarBadgeText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp
        ),
        cardHeaderText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 20.sp
        ),
        cardDescriptionText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp
        ),
        cardAddressText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp
        ),
        tagText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp
        ),
        textFieldText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontSize = 20.sp
        ),
        textFieldLabel = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 20.sp
        ),
        textFieldError = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp
        ),
        buttonText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontSize = 26.sp
        ),
        actionText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontSize = 24.sp
        ),
        hintActionText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp
        ),
        forgotText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 20.sp
        ),
        headerText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp
        ),
        descriptionText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp
        ),
        contactCardNameText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 24.sp
        ),
        contactCardPhoneText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp
        ),
        contactCardButtonText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp
        ),
        chatCardBodyText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontSize = 12.sp
        ),
        chatCardBadgeText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp
        ),
        profileNameText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 25.sp
        ),
        contextMenuButtonText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp
        ),
        settingsButtonText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp
        ),
        photoCardText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 12.sp
        ),
        messageText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp
        ),
        messageTextField = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontSize = 20.sp
        ),
        messageTimeText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontSize = 10.sp
        ),
        chatNameText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 20.sp
        ),
        chatLastActivityText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontSize = 12.sp
        ),
        chatAttachmentsTabText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp
        ),
        chatAttachmentsTitleText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp
        ),
        chatAttachmentsSubtitleText = TextStyle(
            fontFamily = BloggerSans,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 12.sp
        )
    )

    val shapes = SwapShapes(
        swapShape = RoundedCornerShape(10.dp),
        cardTagShape = RoundedCornerShape(5.dp),
        squareCardPhotoShape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        photoShape = CircleShape
    )

    val spacing = SwapSpacing(
        default = 0.dp,
        extraSmall = 4.dp,
        small = 8.dp,
        medium = 16.dp,
        large = 32.dp,
        extraLarge = 64.dp
    )

    CompositionLocalProvider(
        LocalSwapColors provides colors,
        LocalSwapTypes provides types,
        LocalSwapShapes provides shapes,
        LocalSwapSpacing provides spacing,
        content = content
    )
}