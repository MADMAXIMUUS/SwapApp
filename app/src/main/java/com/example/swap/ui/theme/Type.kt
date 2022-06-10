package com.example.swap.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.swap.R

val BloggerSans = FontFamily(
        Font(R.font.blogger_sans_bold, FontWeight.Bold, FontStyle.Normal),
        Font(R.font.blogger_sans_light, FontWeight.Light, FontStyle.Normal),
        Font(R.font.blogger_sans_medium, FontWeight.Medium, FontStyle.Normal),
        Font(R.font.blogger_sans_medium_italic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.blogger_sans_light_italic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.blogger_sans_bold_italic, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.blogger_sans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.blogger_sans, FontWeight.Normal, FontStyle.Normal)
)

val Typography = Typography(
        h1 = TextStyle(
                fontFamily = BloggerSans,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontSize = 26.sp
        ),
        h2 = TextStyle(
                fontFamily = BloggerSans,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontSize = 22.sp
        ),
        h3 = TextStyle(
                fontFamily = BloggerSans,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontSize = 16.sp
        ),
        button = TextStyle(
                fontFamily = BloggerSans,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Normal,
                fontSize = 24.sp

        ),
        caption = TextStyle(
                fontFamily = BloggerSans,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontSize = 12.sp
        ),
        body1 = TextStyle(
                fontFamily = BloggerSans,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 20.sp
        ),
        body2 = TextStyle(
                fontFamily = BloggerSans,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontSize = 14.sp
        ),
        subtitle1 = TextStyle(
                fontFamily = BloggerSans,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Normal,
                fontSize = 12.sp
        )
)
