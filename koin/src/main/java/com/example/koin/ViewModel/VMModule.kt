package com.example.koin.ViewModel

import com.example.koin.R
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val vmModule = module {

    factory { VMRepository(androidContext().getString(R.string.family))} bind UserInfo::class
    viewModel { VMViewModel(get()) }
}