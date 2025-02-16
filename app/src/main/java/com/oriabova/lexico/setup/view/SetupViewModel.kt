package com.oriabova.lexico.setup.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oriabova.lexico.setup.data.SetupDetails
import com.oriabova.lexico.setup.domain.StoreSetupDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val storeSetupDetailsUseCase: StoreSetupDetailsUseCase,
) : ViewModel() {

    val setupState = MutableStateFlow(SetupState.Welcome)

    fun saveSetup() {
        viewModelScope.launch {
            storeSetupDetailsUseCase(SetupDetails("UA", "Advanced", "5/day"))
        }
    }

}

enum class SetupState {
    Welcome,
    Setup,
    Complete
}