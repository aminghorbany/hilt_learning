package com.example.koin.qualifiers

import org.koin.core.qualifier.named
import org.koin.dsl.module

class Human(private val name : String , private val family : String) {
    fun getInfo() = "$name | $family"
}

fun provideName() = "Amin"
fun provideFamily() = "Ghorbani"

val infoModule = module {
    single(named(NAME)) { provideName() }
    factory(named(FAMILY)) { provideFamily() }
    factory { Human(get(named(FAMILY)) , get(named(NAME))) }
}

const val NAME = "name"
const val FAMILY = "family"