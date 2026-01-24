package com.oriabova.lexico.setup.di

import com.oriabova.lexico.setup.view.SetupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val viewModelModule = module {
    viewModelOf(::SetupViewModel)
}

val setupFeatureModule = module {
    includes(viewModelModule)
}
