package com.example.koin.interfacee

class CarImpl : Car , Owner , Number {
    override fun model() = "BMW Series 111"
    override fun ownerName()= "Amin"
    override fun phone()= "09037052133"
}


class CarInfo(private val carImpl: CarImpl) {
    fun getCarInfo() = "${carImpl.model()} | ${carImpl.ownerName()} | ${carImpl.phone()}"
}