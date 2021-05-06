package com.example.buttsweetsfinal.koin

import com.example.buttsweetsfinal.base.modules.accountRepoModule
import com.example.buttsweetsfinal.base.modules.accountViewModelModule
import com.example.buttsweetsfinal.base.modules.baseRepoModule
import com.example.buttsweetsfinal.base.modules.baseViewModelModule
import com.example.buttsweetsfinal.modules.apiModule
import com.example.buttsweetsfinal.modules.retrofitModule
import org.koin.core.module.Module


fun getListOfModules(): List<Module> {

    return (listOf(
        apiModule,
        retrofitModule,
        accountRepoModule,
        accountViewModelModule,
        baseRepoModule,
        baseViewModelModule
    ))
}