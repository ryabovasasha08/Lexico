package com.oriabova.lexico.home.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class StoredWords(
    val words: List<GeneratedWordEntity>,
) {
    companion object {
        val Empty = StoredWords(emptyList())
    }
}