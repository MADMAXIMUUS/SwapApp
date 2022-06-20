package com.example.swap.domain.models

data class Tag(
    val id: String = "",
    var text: String =""
){
    override fun equals(other: Any?): Boolean {
        return (other as Tag).text == text
    }
}