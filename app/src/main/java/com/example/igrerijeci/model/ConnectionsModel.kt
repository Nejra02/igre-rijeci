// model/WordItem.kt
package com.example.igrerijeci.model

data class WordItem(
    val text: String,
    val group: Int,
    var isSelected: Boolean = false,
    var isFound: Boolean = false
)

