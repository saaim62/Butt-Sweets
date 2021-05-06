package com.example.buttsweetsfinal.base.modules

import com.example.buttsweetsfinal.base.models.BaseRepo
import com.example.buttsweetsfinal.base.viewmodels.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val baseViewModelModule = module {
    viewModel { BaseViewModel(get()) }
}

val baseRepoModule = module {
    factory { BaseRepo(get()) }
}