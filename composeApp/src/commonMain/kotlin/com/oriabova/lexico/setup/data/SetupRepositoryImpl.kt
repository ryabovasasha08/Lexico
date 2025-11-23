package com.oriabova.lexico.setup.data

import com.oriabova.lexico.setup.domain.SetupRepository
import kotlinx.coroutines.flow.Flow

class SetupRepositoryImpl(
    private val setupLocalDataSource: SetupLocalDataSource
) : SetupRepository {
   override fun observeSetupDetails(): Flow<SetupDetails> {
        return setupLocalDataSource.observeSetupDetails()
    }

    override suspend fun storeSetupDetails(setupDetails: SetupDetails) {
        setupLocalDataSource.storeSetupDetails(setupDetails)
    }
}