package com.example.koin.simple

import org.koin.dsl.module

val userModule = module {
    single { User() }
    single { UserInfo(get()) }
}