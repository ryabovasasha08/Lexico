package com.oriabova.lexico.setup.data

import com.oriabova.lexico.localstorage.LocalStorage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val SETUP_DETAILS = "setup_details"

class SetupLocalDataSource @Inject constructor(
    private val localStorage: LocalStorage
){
    fun observeSetupDetails(): Flow<SetupDetails?> {
        return localStorage.observeData(SETUP_DETAILS, SetupDetails::class.java, null)
    }

    suspend fun storeSetupDetails(setupDetails: SetupDetails) {
        localStorage.putData(SETUP_DETAILS, setupDetails, SetupDetails::class.java)
    }
}

