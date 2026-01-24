package com.oriabova.lexico.utils

import java.util.Locale

actual fun getAvailableLanguages(): List<Language> {
    return Locale.getAvailableLocales()
        .map {
            Language(
                it.toLanguageTag(),
                it.getDisplayLanguage(Locale.getDefault())
            )
        }
        .distinctBy { it.name }
}