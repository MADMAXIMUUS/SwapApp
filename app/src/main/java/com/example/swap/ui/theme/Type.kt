package com.example.swap.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.swap.R

val BlackerDisplay = FontFamily(
        Font(R.font.blackerdisplay_bold, FontWeight.Bold),
        Font(R.font.blackerdisplay_bolditalic, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.blackerdisplay_extrabold, FontWeight.ExtraBold),
        Font(R.font.blackerdisplay_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
        Font(R.font.blackerdisplay_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.blackerdisplay_light, FontWeight.Light, FontStyle.Normal),
        Font(R.font.blackerdisplay_lightitalic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.blackerdisplay_medium, FontWeight.Medium, FontStyle.Normal),
        Font(R.font.blackerdisplay_mediumitalic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.blackerdisplay_regular, FontWeight.Normal, FontStyle.Normal)
)

val BlackerText = FontFamily(
        Font(R.font.blackertext_bold, FontWeight.Bold),
        Font(R.font.blackertext_bolditalic, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.blackertext_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.blackertext_light, FontWeight.Light, FontStyle.Normal),
        Font(R.font.blackertext_lightitalic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.blackertext_medium, FontWeight.Medium, FontStyle.Normal),
        Font(R.font.blackertext_mediumitalic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.blackertext_regular, FontWeight.Normal, FontStyle.Normal)
)

val Typography = Typography(
        h1 = TextStyle(
                fontFamily = BlackerDisplay,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontSize = 20.sp
        ),
        h2 = TextStyle(
                fontFamily = BlackerDisplay,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Normal,
                fontSize = 20.sp
        ),
        h3 = TextStyle(
                fontFamily = BlackerDisplay,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp
        ),
        caption = TextStyle(
                fontFamily = BlackerDisplay,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Normal,
                fontSize = 12.sp
        ),
        body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 14.sp
        ),
        body2 = TextStyle(
                fontFamily = BlackerText,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 20.sp
        ),
        subtitle1 = TextStyle(
                fontFamily = BlackerText,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 14.sp
        )
)
