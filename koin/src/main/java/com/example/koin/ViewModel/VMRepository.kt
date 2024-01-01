package com.example.koin.ViewModel

class VMRepository(private val family : String) : UserInfo {
    override fun userName()="Amin $family"
}