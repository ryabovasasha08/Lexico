package com.oriabova.lexico.setup.data

import com.oriabova.lexico.localstorage.LocalStorage
import com.oriabova.lexico.setup.domain.model.SetupDetails
import kotlinx.coroutines.flow.Flow

private const val SETUP_DETAILS = "setup_details"

class SetupLocalDataSource(
    private val localStorage: LocalStorage
) {
    fun observeSetupDetails(): Flow<SetupDetails> {
        return localStorage.observeData(
            SETUP_DETAILS,
            SetupDetails::class,
            SetupDetails.initial()
        )
    }

    suspend fun getSetupDetails(): SetupDetails {
        return localStorage.getData(
            SETUP_DETAILS,
            SetupDetails::class,
            SetupDetails.initial()
        )
    }

    suspend fun storeSetupDetails(setupDetails: SetupDetails) {
        localStorage.putData(SETUP_DETAILS, setupDetails, SetupDetails::class)
    }
}

