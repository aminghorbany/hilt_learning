package com.example.koin.simple

class UserInfo(private val user: User) {
    fun userName() = "${user.name} - ${user.family}"

    fun fullInfo() = "data : ${user.name} - ${user.family} - ${user.site}"

    fun userSite() = "your site : ${user.site}"
}