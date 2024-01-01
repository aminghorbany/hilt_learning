package com.example.koin.interfacee

import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module

val carModule = module {
//    factory<Car> { CarImpl() }
//    factory{ CarImpl() } bind Car::class
    factory{ CarImpl() } binds arrayOf(Car::class , Owner::class , Number::class)
    factory { CarInfo(get()) }
}