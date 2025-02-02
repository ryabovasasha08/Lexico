package com.oriabova.lexico.home.view

import androidx.lifecycle.ViewModel
import com.oriabova.lexico.setup.domain.ObserveSetupStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    observeSetupStateUseCase: ObserveSetupStateUseCase
) : ViewModel() {
    val setupStateFlow = observeSetupStateUseCase()
}