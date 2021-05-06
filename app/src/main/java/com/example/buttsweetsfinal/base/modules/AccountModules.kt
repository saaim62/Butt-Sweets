package com.example.buttsweetsfinal.base.modules

import com.example.buttsweetsfinal.base.models.AccountRepo
import com.example.buttsweetsfinal.viewmodel.ActivityProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountViewModelModule = module {
    viewModel { ActivityProductViewModel(get()) }
}

val accountRepoModule = module {
    factory { AccountRepo(get()) }
}