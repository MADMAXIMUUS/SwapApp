package com.example.swap.core.presentation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val American_Yellow = Color(0xFFEDB408)
val Chocolate = Color(0xFFCC7112)
val Prussian_Blue = Color(0xFF002B52)
val Yankees_Blue = Color(0xFF16243C)
val Chinese_Black = Color(0xFF121212)
val Raisin_Black = Color(0xFF202020)
val Gray = Color(0xFFBBBBBB)
val Dark_Charcoal = Color(0xFF323232)
val Quick_Silver = Color(0xFFA1A1A1)
val Onyx = Color(0xFF393939)
val Silver_Sand = Color(0xFFC4C4C4)
val Bright_Gray = Color(0xFFEEEEEE)
val Light_Gray = Color(0xFFD4D4D4)
val Davy_Grey =Color(0xFF595959)
val Baltic_Sea = Color(0xFF262626)
val Mine_Shaft = Color(0xFF212121)
val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)
val Red = Color(0xFFFF0000)

data class SwapColors(
    val primaryBackgroundColor: Color,
    val cardBackgroundColor: Color,
    val barColor: Color,
    val headerTextColor: Color,
    val descriptionColor: Color,
    val cardHeaderTextColor: Color,
    val cardDescriptionTextColor: Color,
    val cardAddressTextColor: Color,
    val barTitleSelectedColor: Color,
    val barTitleUnselectedColor: Color,
    val topBarTitleColor: Color,
    val barIconTintColor: Color,
    val forgotTextColor: Color,
    val badgeBottomBarColor: Color,
    val badgeBottomBarTextColor: Color,
    val badgeChatCardBackgroundColor: Color,
    val badgeChatCardTextColor: Color,
    val chatCardLastMessageColor: Color,
    val chatCardMemberColor: Color,
    val textFieldBackgroundColor: Color,
    val textFieldTextColor: Color,
    val textFieldLabelColor: Color,
    val buttonTextColor: Color,
    val buttonBackgroundColor: Color
)

val SwapDarkColorPalette = SwapColors(
    primaryBackgroundColor = Chinese_Black,
    cardBackgroundColor = Dark_Charcoal,
    barColor = Onyx,
    headerTextColor = American_Yellow,
    descriptionColor = White,
    cardHeaderTextColor = White,
    cardDescriptionTextColor = Quick_Silver,
    cardAddressTextColor = Gray,
    barTitleSelectedColor = Chocolate,
    barTitleUnselectedColor = American_Yellow,
    topBarTitleColor = American_Yellow,
    barIconTintColor = American_Yellow,
    forgotTextColor = American_Yellow,
    badgeBottomBarColor = Red,
    badgeBottomBarTextColor = White,
    badgeChatCardBackgroundColor = Baltic_Sea,
    badgeChatCardTextColor = White,
    chatCardLastMessageColor = Silver_Sand,
    chatCardMemberColor = White,
    textFieldBackgroundColor = Dark_Charcoal,
    textFieldTextColor = White,
    textFieldLabelColor = American_Yellow,
    buttonTextColor = Chinese_Black,
    buttonBackgroundColor = Chocolate
)

val SwapLightColorPalette = SwapColors(
    primaryBackgroundColor = White,
    cardBackgroundColor = Bright_Gray,
    barColor = White,
    headerTextColor = Prussian_Blue,
    descriptionColor = Black,
    cardHeaderTextColor = Prussian_Blue,
    cardDescriptionTextColor = Davy_Grey,
    cardAddressTextColor = Gray,
    barTitleSelectedColor = Prussian_Blue,
    barTitleUnselectedColor = Light_Gray,
    topBarTitleColor = Prussian_Blue,
    barIconTintColor = Prussian_Blue,
    forgotTextColor = Prussian_Blue,
    badgeBottomBarColor = Red,
    badgeBottomBarTextColor = White,
    badgeChatCardBackgroundColor = Light_Gray,
    badgeChatCardTextColor = Yankees_Blue,
    chatCardLastMessageColor = Yankees_Blue,
    chatCardMemberColor = Prussian_Blue,
    textFieldBackgroundColor = Bright_Gray,
    textFieldTextColor = Black,
    textFieldLabelColor = Prussian_Blue,
    buttonTextColor = White,
    buttonBackgroundColor = Chocolate
)

val LocalSwapColors = staticCompositionLocalOf<SwapColors> {
    error("No colors provided")
}