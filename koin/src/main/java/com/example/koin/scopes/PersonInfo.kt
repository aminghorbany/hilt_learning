package com.example.koin.scopes

import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module
import org.koin.dsl.scoped

class PersonInfo {
    fun showInfo(): String {
        return "Amin Ghorbany"
    }
}

@KoinReflectAPI
val personModule = module {
//    single { PersonInfo() }

    // doing with scope ->
    scope<ScopesActivity> {
            // step 1 : so now don't need single or factory
            // what we want to inject
            scoped<PersonInfo>() //set propagate annotation warning @KoinReflectAPI
            // step 2 : define it in App
    }
}