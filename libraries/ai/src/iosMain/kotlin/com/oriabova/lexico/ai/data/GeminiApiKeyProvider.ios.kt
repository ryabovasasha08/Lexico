package com.oriabova.lexico.ai.data

import platform.Foundation.NSBundle

internal actual fun getAiApiKey(): String {
    return NSBundle.mainBundle.objectForInfoDictionaryKey("GeminiApiKey") as? String ?: ""
}
