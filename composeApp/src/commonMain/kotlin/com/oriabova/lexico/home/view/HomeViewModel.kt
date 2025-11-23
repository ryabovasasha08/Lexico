package com.oriabova.lexico.home.view

import androidx.lifecycle.ViewModel
import com.oriabova.lexico.setup.domain.ObserveSetupStateUseCase

class HomeViewModel(
     observeSetupStateUseCase: ObserveSetupStateUseCase
) : ViewModel() {
    val setupStateFlow = observeSetupStateUseCase()
}