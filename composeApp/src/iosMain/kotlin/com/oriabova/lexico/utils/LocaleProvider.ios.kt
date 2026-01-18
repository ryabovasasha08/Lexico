package com.oriabova.lexico.utils

import platform.Foundation.NSLocale
import platform.Foundation.NSLocaleIdentifier
import platform.Foundation.availableLocaleIdentifiers
import platform.Foundation.currentLocale

actual fun getAvailableLanguages(): List<Language> {
    @Suppress("UNCHECKED_CAST")

    val identifiers = NSLocale.availableLocaleIdentifiers() as List<String>
    val currentLocale = NSLocale.currentLocale()

    return identifiers
        .map { it.substringBefore('_') }
        .mapNotNull { code ->
            val name = currentLocale.displayNameForKey(NSLocaleIdentifier, code)
            name?.let { Language(code, it) }
        }
        .distinctBy { it.name }
}