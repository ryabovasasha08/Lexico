package com.oriabova.lexico.home.view.model

internal sealed interface HomeUiEvent {
    data object OnSaveWord : HomeUiEvent
    data object OnSkipWord : HomeUiEvent
    data object OnAudioPlay : HomeUiEvent
}
