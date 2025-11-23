package com.oriabova.lexico.utils

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual fun getAvailableLanguages(): List<String> {
    @Suppress("UNCHECKED_CAST")
    val languageCodes = NSLocale.isoLanguageCodes as List<String>

    return languageCodes
        // 2. Map each code to its display name, localized to the user's device setting
        .mapNotNull { languageCode ->
            NSLocale.currentLocale.localizedStringForLanguageCode(languageCode)
        }
        .distinct()

}