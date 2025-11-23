package com.oriabova.lexico.utils

import java.util.Locale

actual fun getAvailableLanguages(): List<String> {
    return Locale.getAvailableLocales()
        .map { it.getDisplayLanguage(Locale.getDefault()) }
        .distinct()
}