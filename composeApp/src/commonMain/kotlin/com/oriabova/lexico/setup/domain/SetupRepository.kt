package com.oriabova.lexico.setup.domain

import com.oriabova.lexico.setup.domain.model.SetupDetails
import kotlinx.coroutines.flow.Flow

interface SetupRepository {
    fun observeSetupDetails(): Flow<SetupDetails>
    suspend fun storeSetupDetails(setupDetails: SetupDetails)
}