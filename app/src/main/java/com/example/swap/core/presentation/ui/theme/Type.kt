package com.example.swap.core.presentation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.swap.R

data class SwapTypes(
    val topBarTitleText: TextStyle,
    val bottomBarText: TextStyle,
    val bottomBarBadgeText: TextStyle,
    val cardHeaderText: TextStyle,
    val cardDescriptionText: TextStyle,
    val cardAddressText: TextStyle,
    val tagText: TextStyle,
    val textFieldText: TextStyle,
    val textFieldLabel: TextStyle,
    val textFieldError: TextStyle,
    val buttonText: TextStyle,
    val actionText: TextStyle,
    val hintActionText: TextStyle,
    val forgotText: TextStyle,
    val headerText: TextStyle,
    val descriptionText: TextStyle,
    val contactCardNameText: TextStyle,
    val contactCardPhoneText: TextStyle,
    val contactCardButtonText: TextStyle,
    val chatCardBodyText: TextStyle,
    val chatCardBadgeText: TextStyle,
    val profileNameText: TextStyle,
    val contextMenuButtonText: TextStyle,
    val settingsButtonText: TextStyle,
    val photoCardText: TextStyle,
    val messageText: TextStyle,
    val messageTextField: TextStyle,
    val messageTimeText: TextStyle,
    val chatNameText: TextStyle,
    val chatLastActivityText: TextStyle,
    val chatAttachmentsTabText: TextStyle,
    val chatAttachmentsTitleText: TextStyle,
    val chatAttachmentsSubtitleText: TextStyle
)

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

val LocalSwapTypes = staticCompositionLocalOf<SwapTypes> {
    error("No font provided")
}
