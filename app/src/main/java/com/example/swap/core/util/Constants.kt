package com.example.swap.core.util

object Constants {

    const val SIGN_IN_TYPE_ANONYMOUS = "Anonymous"
    const val SIGN_IN_TYPE_EMAIL = "Email"

    const val USER_COLLECTION = "users"
    const val ADVERT_COLLECTION = "adverts"
    const val CHAT_COLLECTION = "chats"
    const val MESSAGE_COLLECTION = "messages"
    const val TAG_COLLECTION = "tags"

    const val USER_DOCUMENT_NAME = "name"
    const val USER_DOCUMENT_EMAIL = "email"
    const val USER_DOCUMENT_PHONE = "phone"
    const val USER_DOCUMENT_ID = "id"
    const val USER_DOCUMENT_RATING = "rating"
    const val USER_DOCUMENT_AVATAR = "avatarUrl"

    const val ADVERT_DOCUMENT_ID = "id"
    const val ADVERT_DOCUMENT_AUTHOR_ID = "authorId"
    const val ADVERT_DOCUMENT_TITLE = "title"
    const val ADVERT_DOCUMENT_CREATED_AT="createdAt"
    const val ADVERT_DOCUMENT_DESCRIPTION = "description"
    const val ADVERT_DOCUMENT_IMAGE_LIST = "imageListUrls"
    const val ADVERT_DOCUMENT_TAGS = "tags"

    const val TAGS_DOCUMENT_ID="id"
    const val TAGS_DOCUMENT_TEXT="text"

    const val SPLASH_SCREEN_DURATION = 2000L

    const val MAX_POST_DESCRIPTION_LINES = 3

    const val MIN_USERNAME_LENGTH = 3
    const val MIN_PASSWORD_LENGTH = 3

    const val DEFAULT_PAGE_SIZE = 20

    const val KEY_JWT_TOKEN = "jwt_token"
    const val KEY_USER_ID = "userId"

    const val SHARED_PREF_NAME = "shared_pref"

    const val RECONNECT_INTERVAL = 5000L
}