package com.oriabova.lexico.utils

import platform.Foundation.NSLocale
import platform.Foundation.NSLocaleIdentifier
import platform.Foundation.availableLocaleIdentifiers
import platform.Foundation.currentLocale

actual fun getAvailableLanguages(): List<String> {
    @Suppress("UNCHECKED_CAST")

    val identifiers = NSLocale.availableLocaleIdentifiers() as List<String>
    val currentLocale = NSLocale.currentLocale()

    return identifiers
        .mapNotNull { code -> currentLocale.displayNameForKey(NSLocaleIdentifier, code) }
        .distinct()
}